package com.tsongkha.trademeexample.stories.listings.repository

import android.content.res.Resources
import com.google.gson.GsonBuilder
import com.tsongkha.trademeexample.R
import com.tsongkha.trademeexample.stories.listings.GsonAdaptersListing
import com.tsongkha.trademeexample.stories.listings.GsonAdaptersSearchResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rawsond on 9/12/17.
 */
@RunWith(RobolectricTestRunner::class)
class SearchWebServiceTest {

    //Robolectric shadows
    lateinit var res : Resources

    //mocks
    lateinit var mockWebServer: MockWebServer

    //system under test
    lateinit var searchWebService: SearchWebService

    @Before
    fun setUp() {
        res = RuntimeEnvironment.application.resources
        mockWebServer = MockWebServer()

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE }
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val gson = GsonBuilder()
                .registerTypeAdapterFactory(GsonAdaptersSearchResult())
                .registerTypeAdapterFactory(GsonAdaptersListing())
                .create()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(mockWebServer.url("")).build()

        searchWebService = retrofit.create(SearchWebService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSearchResultTwoRows() {
        //arrange
        MockResponse().apply {
            setBody(res.getString(R.string.search_two_row_response_body))
            setResponseCode(200)
            mockWebServer.enqueue(this)
        }

        //act
        val searchResult = searchWebService.byCategory("3720", 2).firstOrError().blockingGet()
        mockWebServer.takeRequest()

        //assert
        assertEquals(2, searchResult.listings().size)
        val first = searchResult.listings().first()
        assertEquals(6229734, first.id())
        assertEquals("Mens Fashionista Overcoat - L", first.title())
        assertEquals("https://images.tmsandbox.co.nz/photoserver/thumb/893921.jpg", first.pictureHref())
    }
}