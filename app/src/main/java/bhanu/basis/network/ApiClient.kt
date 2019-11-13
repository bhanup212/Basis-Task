package bhanu.basis.network

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by: Bhanu Prakash
 * Created on:20:24, 11,November,2019
 */
interface ApiClient {

    @GET(ApiUrls.URL_SWIPABLE_LIST)
    fun getCardList():Single<String>
}