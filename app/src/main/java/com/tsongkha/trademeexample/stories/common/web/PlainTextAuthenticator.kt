package com.tsongkha.trademeexample.stories.common.web

import com.tsongkha.trademeexample.BuildConfig
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

/**
 * Created by rawsond on 9/12/17.
 */
class PlainTextAuthenticator @Inject constructor() : Authenticator {

    override fun authenticate(route: Route?, response: Response?): Request? {
        return response?.request()
                ?.newBuilder()
                ?.addHeader("Authorization", "OAuth oauth_consumer_key=\""
                        + BuildConfig.CONSUMER_KEY + "\", oauth_signature_method=\"PLAINTEXT\", oauth_signature=\""
                        + BuildConfig.CONSUMER_SECRET + "&\"")
                ?.build()
    }
}