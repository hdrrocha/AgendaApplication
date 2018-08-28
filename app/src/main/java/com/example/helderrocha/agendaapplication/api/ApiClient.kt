package com.example.helderrocha.agendaapplication.api

import android.os.Parcel
import android.os.Parcelable
import com.example.helderrocha.agendaapplication.model.*
import com.example.helderrocha.agendaapplication.view_model.PeopleListViewModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ApiClient @Inject constructor(private val gitApi: AgrApi) {

    fun organizations(page: Int):Single<OrganizationList> {
        return gitApi.organizations(page)
    }

    fun people(page: Int):Single<PeopleList> {
        return gitApi.people(page)
    }

    fun peopleId(id: Int):Single<PersonObject> {
        return gitApi.peopleId(id)
    }

    fun organizationId(id: Int):Single<OrganizationObject> {
        return gitApi.organizationId(id)
    }

}