package com.rafaelfelipeac.marvelapp.core.network

import com.rafaelfelipeac.marvelapp.base.equalTo
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class NetworkTest {

    @Test
    fun `GICEN a request WHEN it returns successfully THEN it should emit the result as Success`() {
        runBlocking {
            // given
            val lambdaResult = true
            val success = ResultWrapper.Success(lambdaResult)

            // when
            val result = Network.request { lambdaResult }

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN a request WHEN it throws IOException THEN it should emit the result as NetworkError`() {
        runBlocking {
            // given
            val throwable = IOException()
            val networkError = ResultWrapper.NetworkError(throwable)

            // when
            val result = Network.request { throw throwable }

            // then
            result equalTo networkError
        }
    }

    @Test
    fun `GIVEN a request WHEN it throws HttpException THEN it should emit the result as GenericError`() {
        val errorBody =
            "{\"errors\": [\"Not Found\"]}".toResponseBody("application/json".toMediaTypeOrNull())

        runBlocking {
            // given
            val throwable = HttpException(Response.error<Any>(404, errorBody))
            val genericError = ResultWrapper.GenericError(
                404,
                "HTTP 404 Response.error()",
                throwable
            )

            // when
            val result = Network.request { throw throwable }

            // then
            result equalTo genericError
        }
    }

    @Test
    fun `GIVEN a request WHEN it throws unknown exception THEN it should emit the result as GenericError`() {
        runBlocking {
            // given
            val throwable = Exception()
            val genericError = ResultWrapper.GenericError(null, null, throwable)

            // when
            val result = Network.request { throw throwable }

            // then
            result equalTo genericError
        }
    }
}
