/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package co.ld.codechallenge.util

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestHelper constructor(private val mTestInstance: Any?) {
    private var mRetrofit: Retrofit? = null

    /**
     * Add files under `/src/test/resources`
     * Add files under `/src/androidTest/resources`
     *
     * @param filePath location of file to be read, relative path.
     * @return Contents in file.
     */
    fun read(filePath: String): String {
        return mTestInstance!!.javaClass.getResource("/$filePath").readText()
    }

    @Synchronized
    fun getExecutor(baseUrl: String): Retrofit {
        if (mRetrofit == null) {
            val httpClient = OkHttpClient.Builder()
            mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonCoverter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return mRetrofit!!
    }

    companion object {

        fun with(instance: Any): TestHelper {
            return TestHelper(instance)
        }

        private val gsonCoverter: GsonConverterFactory
            get() = GsonConverterFactory.create(
                GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .setLenient()
                    .create()
            )
    }
}
