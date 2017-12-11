package com.tsongkha.trademeexample.stories.categories.repository

import android.content.res.Resources
import com.google.gson.GsonBuilder
import com.tsongkha.trademeexample.R
import com.tsongkha.trademeexample.stories.categories.GsonAdaptersCategory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by rawsond on 7/12/17.
 */
@RunWith(RobolectricTestRunner::class)
class CategoryWebServiceTest {

    //Robolectric shadows
    lateinit var res : Resources

    //mocks
    lateinit var mockWebServer: MockWebServer

    //system under test
    lateinit var categoryWebService: CategoryWebService

    @Before
    fun setUp() {
        res = RuntimeEnvironment.application.resources
        mockWebServer = MockWebServer()

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE }
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val gson = GsonBuilder()
                .registerTypeAdapterFactory(GsonAdaptersCategory())
                .create()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(mockWebServer.url("")).build()

        categoryWebService = retrofit.create(CategoryWebService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testChildDepth0() {
        //arrange
        MockResponse().apply {
            setBody(res.getString(R.string.category_child_depth_0_response_body))
            setResponseCode(200)
            mockWebServer.enqueue(this)
        }

        //act
        val category = categoryWebService.byNumber("0001", 0).firstOrError().blockingGet()
        val recordedRequest = mockWebServer.takeRequest()

        //assert
        assertEquals("/v1/Categories/0001.json?depth=0", recordedRequest.path)
        assertEquals("Trade Me Motors", category.name())
        assertEquals("0001-", category.number())
        assertEquals(false, category.isLeaf())
        assertEquals("/Trade-Me-Motors", category.path())
    }


    @Test
    fun testChildDepth1() {
        //arrange
        MockResponse().apply {
            setBody(res.getString(R.string.category_child_depth_1_response_body))
            setResponseCode(200)
            mockWebServer.enqueue(this)
        }

        //act
        val category = categoryWebService.byNumber("0001", 1).firstOrError().blockingGet()
        val recordedRequest = mockWebServer.takeRequest()

        //assert
        assertEquals("/v1/Categories/0001.json?depth=1", recordedRequest.path)

        val subcategories = category.subcategories()!!
        assertEquals(13, subcategories.size)

        val cars = subcategories.first { it.name() == "Cars" }
        assertEquals("0001-0268-", cars.number())
        assertEquals(false, cars.isLeaf)
        assertEquals("/Trade-Me-Motors/Cars", cars.path())
        assertNull(cars.subcategories())
    }


    @Test
    fun testRootDepth1() {
        //arrange
        MockResponse().apply {
            setBody(res.getString(R.string.category_root_depth_1_response_body))
            setResponseCode(200)
            mockWebServer.enqueue(this)
        }

        //act
        val category = categoryWebService.root(1).firstOrError().blockingGet()
        val recordedRequest = mockWebServer.takeRequest()

        //assert
        assertEquals("/v1/Categories.json?depth=1", recordedRequest.path)

        val subcategories = category.subcategories()!!
        assertEquals(27, subcategories.size)
    }
}