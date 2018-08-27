package com.example.helderrocha.agendaapplication

import android.content.Context
import com.example.helderrocha.agendaapplication.api.NetworkModule
import com.example.helderrocha.agendaapplication.view.OrganizationsListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


@Module(includes = [
    NetworkModule::class,
    SchedulerModule::class
])
class AppModule

@Module
abstract class AndroidInjectorsModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity



}

//@Module
//abstract  class  AndroidSupportInjection {
//    @ContributesAndroidInjector
//    abstract fun organizationsListFragment(): OrganizationsListFragment
//}

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun organizationsListFragment(): OrganizationsListFragment
}

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        AndroidInjectorsModule::class,
        FragmentModule::class

))
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>() {
        @BindsInstance
        abstract fun appContext(appContext: Context): Builder
    }
}