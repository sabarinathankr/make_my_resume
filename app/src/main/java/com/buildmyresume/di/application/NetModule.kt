package com.buildmyresume.di.application

import dagger.Module

@Module(includes = [(ApplicationModule::class)])
class NetModule {
    /* @Provides
     @Singleton
     @OkHttpClientWithoutAES
     internal fun provideClientWithoutEncryption(): OkHttpClient {

         val httpLoggingInterceptor = HttpLoggingInterceptor()
         httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

         return OkHttpClient.Builder()
                 .retryOnConnectionFailure(true)
                 .addInterceptor(httpLoggingInterceptor)
                 .connectTimeout(1, TimeUnit.MINUTES)
                 .writeTimeout(1, TimeUnit.MINUTES)
                 .readTimeout(1, TimeUnit.MINUTES)
                 .build()
     }

     @Provides
     @Singleton
     @RetrofitGoogleAPIs
     internal fun provideRetrofitWithoutEncryption(@OkHttpClientWithoutAES okHttpClient: OkHttpClient): Retrofit {
         return Retrofit.Builder()
                 .baseUrl(URLFactory.getBaseURLForGoogleAPIs())
                 .client(okHttpClient)
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
     }

     *//*
    * *****************************************************
    * With Encryption
    * *****************************************************
    * *//*

    // region With Encryption

    @Provides
    @Singleton
    @HeaderInterceptorWithEncryption
    internal fun provideAESHeaderInterceptor(session: Session): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                    .header(Common.RequestHeaderKey.API_KEY, session.apiKey)
                    .header(Common.RequestHeaderKey.AUTHORIZATION,"Bearer " + session.authorization)
                    .header(Common.RequestHeaderKey.LANGUAGE, "en")
                    .build()

            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    @BodyInterceptorWithEncryption
    internal fun provideAESBodyInterceptor(aesCryptoInterceptor: AESCryptoInterceptor): Interceptor {
        return aesCryptoInterceptor
    }

    @Provides
    @Singleton
    @NetworkInterceptorWithEncryption
    internal fun provideAESNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val code = response.code
            if (code >= 500)
                throw ServerError("Unknown server error", response.body!!.string())
            else if (code == 401 || code == 403)
                throw AuthenticationException()
            response
        }
    }

    @Provides
    @Singleton
    @OkHttpClientWithAES
    internal fun provideClientWithEncryption(
            @HeaderInterceptorWithEncryption headerInterceptor: Interceptor,
            @BodyInterceptorWithEncryption bodyInterceptor: Interceptor,
            @NetworkInterceptorWithEncryption networkInterceptor: Interceptor
    ): OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> DebugLog.d("API = $message") }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(bodyInterceptor)
                .addNetworkInterceptor(networkInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
    }

    @Provides
    @Singleton
    @RetrofitWithAES
    internal fun provideRetrofitWithEncryption(@OkHttpClientWithAES okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(URLFactory.getHttpUrl())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }*/


}