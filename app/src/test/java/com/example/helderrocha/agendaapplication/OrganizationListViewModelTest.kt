package com.example.helderrocha.agendaapplication

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LiveData
import com.example.helderrocha.agendaapplication.api.AgrApi
import com.example.helderrocha.agendaapplication.api.ApiClient
import com.example.helderrocha.agendaapplication.model.OrganizationList
import io.kotlintest.specs.AbstractFreeSpec
import io.kotlintest.specs.FreeSpec
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class OrganizationListViewModelTest: FreeSpec({
    class TestSchedulerProvider : SchedulerProvider {
        override fun mainThread(): Scheduler {
            return TestScheduler()
        }

        override fun io(): Scheduler {
            return TestScheduler()
        }
    }

    val agrApi = Mockito.mock(AgrApi::class.java)
    val apiClient = ApiClient(agrApi)
    val testScheduler = TestScheduler()
    val testSchedulerProvider = TestSchedulerProvider()

    fun mockApiOrganizationsResponse(response: OrganizationList? = null, error: Error? = null) {
        val mockResponse: Observable<OrganizationList> = if (response != null) Observable.just(response) else Observable.error(error)
        Mockito.`when`(agrApi.organizations(Mockito.anyInt()))
                .thenReturn(mockResponse.subscribeOn(testScheduler).observeOn(testScheduler))
    }



    fun <T> observe(result: LiveData<T>, observer: (T?) -> Unit): Unit {
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        result.observe({ lifecycle }, { observer(it) })
    }


})

private fun Any.thenReturn(observeOn: Observable<OrganizationList>?) {
        
}
