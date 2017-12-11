package com.tsongkha.trademeexample.stories.listings

import com.nhaarman.mockito_kotlin.*
import com.tsongkha.trademeexample.stories.common.rx.CompositeDisposableProvider
import com.tsongkha.trademeexample.stories.common.rx.Schedulers
import com.tsongkha.trademeexample.stories.listings.repository.ListingRepository
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

/**
 * Created by rawsond on 11/12/17.
 */
class ListingPresenterTest {

    @Mock lateinit var mockListingRepository : ListingRepository
    @Mock lateinit var mockSchedulers : Schedulers
    @Mock lateinit var mockView : ListingContract.View

    //test helper
    lateinit var testScheduler : TestScheduler

    //system under test
    lateinit var presenter : ListingPresenter

    @Before
    fun setUp() {
        //mocks
        MockitoAnnotations.initMocks(this)

        testScheduler = TestScheduler()
        whenever(mockSchedulers.io()).thenReturn(testScheduler)
        whenever(mockSchedulers.main()).thenReturn(testScheduler)

        //system under test
        presenter = ListingPresenter(mockListingRepository, CompositeDisposableProvider(), mockSchedulers)
    }

    @Test
    fun testLoadListingForNullDoesNothing() {
        //arrange
        presenter.takeView(mockView)

        presenter.loadListings(null)

        verify(mockView, never()).showListingItem(any())
    }

    @Test
    fun testLoadListingForEmptyStringDoesNothing() {
        //arrange
        presenter.takeView(mockView)

        presenter.loadListings("")

        verify(mockView, never()).showListingItem(any())
    }

    @Test
    fun testLoadListingWithCategoryNameShowsEachIndividualListing() {
        //arrange
        presenter.takeView(mockView)
        whenever(mockListingRepository.byCategory(eq("catName"), any())).thenReturn(Observable.just(listing1, listing2))

        //act
        presenter.loadListings("catName")
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        //assert
        inOrder(mockView) {
            verify(mockView).showListingItem(listing1)
            verify(mockView).showListingItem(listing2)
            verifyNoMoreInteractions(mockView)
        }
    }

    private val listing1 = ImmutableListing.builder()
            .id(12345)
            .title("Listing1")
            .pictureHref("http://www.example.com/listing1")
            .build()

    private val listing2 = ImmutableListing.builder()
            .id(54321)
            .title("Listing2")
            .pictureHref("http://www.example.com/listing2")
            .build()
}