package com.example.helderrocha.agendaapplication.api

import android.os.Parcel
import android.os.Parcelable
import com.example.helderrocha.agendaapplication.model.OrganizationList
import com.example.helderrocha.agendaapplication.model.PeopleList
import com.example.helderrocha.agendaapplication.model.PersonModel
import com.example.helderrocha.agendaapplication.model.PersonObject
import com.example.helderrocha.agendaapplication.view_model.PeopleListViewModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ApiClient @Inject constructor(private val gitApi: AgrApi) {
//    fun repositoriesscroll(page: String): Observable<RepositoryResponse> {
//        return gitApi.repositoriesscroll(page)
//    }
//
//    fun pullRequest(name: String, repositorio: String): Observable<List<PullRequest>> {
//        return gitApi.pullRequest(name, repositorio)
//    }

    fun organizations(page: Int):Single<OrganizationList> {
        return gitApi.organizations(page)
    }

    fun people(page: Int):Single<PeopleList> {
        return gitApi.people(page)
    }

    fun peopleId(id: Int):Single<PersonObject> {
        return gitApi.peopleId(id)
    }

}