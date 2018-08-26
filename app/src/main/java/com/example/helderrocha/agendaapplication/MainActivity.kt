package com.example.helderrocha.agendaapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.helderrocha.agendaapplication.model.OrganizationModel
import com.example.helderrocha.agendaapplication.view_model.OrganizationListViewModel
import com.example.helderrocha.agendaapplication.view_model.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.ogaclejapan.smarttablayout.SmartTabLayout
import android.support.v4.view.ViewPager
import com.example.helderrocha.agendaapplication.view.ContactsListFragment
import com.example.helderrocha.agendaapplication.view.OrganizationsListFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                .add("Empresas", OrganizationsListFragment::class.java)
                .add("Contatos", ContactsListFragment::class.java)
                .create())

        val viewPager =   findViewById<View>(R.id.viewPager) as ViewPager
        viewPager.adapter = adapter

        val viewPagerTab = findViewById<View>(R.id.viewPagerTab) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)

    }


}
