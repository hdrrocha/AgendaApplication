package com.example.helderrocha.agendaapplication.api


import com.example.helderrocha.agendaapplication.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface AgrApi {
    companion object {
        const val URL = "https://api.agendor.com.br/v3/"
    }

    @Headers("Authorization: Token 19c7fe22-741d-4b75-b091-52f64c9355ed")
    @GET("organizations?&per_page=20" )
    fun organizations(
            @Query("page") page: Int

    ): Single<OrganizationList>

    @Headers("Authorization: Token 19c7fe22-741d-4b75-b091-52f64c9355ed")
    @GET("people?&per_page=20" )
    fun people(
            @Query("page") page: Int

    ): Single<PeopleList>

    @Headers("Authorization: Token 19c7fe22-741d-4b75-b091-52f64c9355ed")
    @GET("people/{id}" )
    fun peopleId(
            @Path("id") id: Int

    ): Single<PersonObject>

    @Headers("Authorization: Token 19c7fe22-741d-4b75-b091-52f64c9355ed")
    @GET("organizations/{id}" )
    fun organizationId(
            @Path("id") id: Int

    ): Single<OrganizationObject>

}