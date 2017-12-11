package com.tsongkha.trademeexample.stories.categories

import android.content.Intent
import android.net.Uri
import com.nhaarman.mockito_kotlin.*
import com.tsongkha.trademeexample.stories.categories.repository.CategoryRepository
import com.tsongkha.trademeexample.stories.common.rx.CompositeDisposableProvider
import com.tsongkha.trademeexample.stories.common.rx.Schedulers
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import java.net.ConnectException
import java.util.concurrent.TimeUnit

/**
 * Created by rawsond on 11/12/17.
 */
@RunWith(RobolectricTestRunner::class)
class CategoryPresenterTest {

    //mocks
    @Mock lateinit var mockCategoryRepository : CategoryRepository
    @Mock lateinit var mockSchedulers : Schedulers
    @Mock lateinit var mockView : CategoryContract.View

    //captors
    lateinit var intentCaptor : KArgumentCaptor<Intent>

    //test helper
    lateinit var testScheduler : TestScheduler

    //system under test
    lateinit var presenter : CategoryPresenter

    @Before
    fun setUp() {
        //mocks
        MockitoAnnotations.initMocks(this)

        testScheduler = TestScheduler()
        whenever(mockSchedulers.io()).thenReturn(testScheduler)
        whenever(mockSchedulers.main()).thenReturn(testScheduler)

        //captors
        intentCaptor = argumentCaptor<Intent>()

        //system under test
        presenter = CategoryPresenter(mockCategoryRepository, CompositeDisposableProvider(), mockSchedulers)
    }

    @Test
    fun testNextCategoryOnALeafShowsListing() {
        //arrange
        presenter.takeView(mockView)
        val category = ImmutableCategory.builder()
                .name("Leaf")
                .number("12345")
                .isLeaf(true)
                .path("/Leaf")
                .build()

        //act
        presenter.nextCategory(category)

        //assert
        verify(mockView).showNextView(intentCaptor.capture())
        val intent = intentCaptor.firstValue
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals(Uri.parse("tme://listings.trademe.co.nz/12345"), intent.data)
        assertEquals("Leaf", intent.getStringExtra(CategoryContract.EXTRA_CATEGORY_NAME))
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun testNextCategoryOnANonLeafShowsThatCategory() {
        //arrange
        presenter.takeView(mockView)
        val category = ImmutableCategory.builder()
                .name("NonLeaf")
                .number("54321")
                .isLeaf(false)
                .path("/NonLeaf")
                .build()

        //act
        presenter.nextCategory(category)

        //assert
        verify(mockView).showNextView(intentCaptor.capture())
        val intent = intentCaptor.firstValue
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals(Uri.parse("tme://categories.trademe.co.nz/54321"), intent.data)
        assertEquals("NonLeaf", intent.getStringExtra(CategoryContract.EXTRA_CATEGORY_NAME))
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun testLoadCategoriesWithNullArgumentDisplaysSubCategoriesOfRoot() {
        //arrange
        val root = ImmutableCategory.builder()
                .isLeaf(false)
                .name("Root")
                .number("")
                .path("")
                .addSubcategories(subCategory1, subCategory2)
                .build()

        whenever(mockCategoryRepository.root(any())).thenReturn(Observable.just(root))
        presenter.takeView(mockView)

        //act
        presenter.loadCategory(null)
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        //assert
        inOrder(mockView) {
            verify(mockView).showSubcategoryItem(subCategory1)
            verify(mockView).showSubcategoryItem(subCategory2)
            verifyNoMoreInteractions(mockView)
        }
    }

    @Test
    fun testLoadCategoriesWithNumberDisplaysSubCategoriesOfThatCategory() {
        //arrange
        val childCategory = ImmutableCategory.builder()
                .isLeaf(false)
                .name("Child")
                .number("2345")
                .path("2345")
                .addSubcategories(subCategory1, subCategory2)
                .build()

        whenever(mockCategoryRepository.byNumber(eq("2345"), eq(1))).thenReturn(Observable.just(childCategory))
        presenter.takeView(mockView)

        //act
        presenter.loadCategory("2345")
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)


        //assert
        inOrder(mockView) {
            verify(mockView).showSubcategoryItem(subCategory1)
            verify(mockView).showSubcategoryItem(subCategory2)
            verifyNoMoreInteractions(mockView)
        }
    }

    @Test
    fun testErrorOnLoadCategoryWillDisplayError() {
        //arrange
        whenever(mockCategoryRepository.byNumber(eq("2345"), eq(1))).thenReturn(Observable.error(ConnectException("No internet")))
        presenter.takeView(mockView)

        //act
        presenter.loadCategory("2345")
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)


        //assert
        verify(mockView).showError("No internet")
        verifyNoMoreInteractions(mockView)
    }


    private val subCategory1 = ImmutableCategory.builder()
                .isLeaf(true)
                .name("1")
                .number("1")
                .path("1")
                .build()

    private val subCategory2 = ImmutableCategory.builder()
                .isLeaf(false)
                .name("2")
                .number("2")
                .path("2")
                .build()
}

