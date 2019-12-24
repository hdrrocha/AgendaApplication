package com.example.helderrocha.agendaapplication.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ogaclejapan.smarttablayout.SmartTabLayout
import android.support.v4.view.ViewPager
import com.example.helderrocha.agendaapplication.R
import com.example.helderrocha.agendaapplication.view.contact.ContactsListFragment
import com.example.helderrocha.agendaapplication.view.organization.OrganizationsListFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()!!.setElevation(0F)
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
