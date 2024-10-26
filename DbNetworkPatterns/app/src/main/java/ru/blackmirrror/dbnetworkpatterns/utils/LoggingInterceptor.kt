package ru.blackmirrror.dbnetworkpatterns.task3

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        Log.d("LoggingInterceptor", "Response Code: ${response.code}")

        return response
    }
}