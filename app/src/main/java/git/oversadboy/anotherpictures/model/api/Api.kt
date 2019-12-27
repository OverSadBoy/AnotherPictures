package git.oversadboy.anotherpictures.model.api

import git.oversadboy.anotherpictures.model.pojo.CollectionImage
import git.oversadboy.anotherpictures.model.pojo.Image
import git.oversadboy.anotherpictures.model.pojo.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/photos")
    fun getPhotos(@Query("page") page: Int): Call<List<Image>>

    @GET("/search/photos")
    fun searchPhotos(@Query("query") query: String, @Query("page") page: Int): Call<SearchResponse>

    @GET("/collections")
    fun getCollections(@Query("page") page: Int): Call<List<CollectionImage>>

    @GET("/collections/{id}/photos")
    fun getCollectionPhotos(@Path("id") id: String, @Query("page") page: Int): Call<List<Image>>

}