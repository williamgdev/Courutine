package com.git.williamgdev.fr.network

import retrofit2.Retrofit

inline fun <reified T> ApiContext(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}