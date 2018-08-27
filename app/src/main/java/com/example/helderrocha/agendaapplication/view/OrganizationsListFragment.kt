package com.example.helderrocha.agendaapplication.view


import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.helderrocha.agendaapplication.OrganizationAdapter

import com.example.helderrocha.agendaapplication.R
import com.example.helderrocha.agendaapplication.model.OrganizationModel
import com.example.helderrocha.agendaapplication.view_model.OrganizationListViewModel
import com.example.helderrocha.agendaapplication.view_model.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.organizations_list_fragment.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */



class OrganizationsListFragment : Fragment() {

    @Inject
    lateinit var organizationsVMFactory: ViewModelFactory<OrganizationListViewModel>

    private val organizationsViewModel by lazy {
        ViewModelProviders.of(this, organizationsVMFactory)[OrganizationListViewModel::class.java]
    }

    protected val ItemsObserver = Observer<List<OrganizationModel>>(::onItemsFetched)

    private lateinit var adapter: OrganizationAdapter

    var layoutManager = LinearLayoutManager(context)

    var totalItemCount: Int = 0
    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var loading: Boolean = false
    var page: Int = 1

    var recyclerViewF: RecyclerView? = null
    lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.organizations_list_fragment, container, false)


        recyclerViewF = view.findViewById(R.id.recyclerView) as RecyclerView // Add this
        recyclerViewF!!.layoutManager = LinearLayoutManager(activity)
        progressBar = view.findViewById(R.id.progressBar)as ProgressBar


        organizationsViewModel.data.observe(this, ItemsObserver)
        organizationsViewModel.getOrganizatons(1)
        return  view

    }

    private fun onItemsFetched(list: List<OrganizationModel>?) {
        if (list != null) {
            setUpdateAdapter(list)
        }


    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun setUpdateAdapter(organizationList: List<OrganizationModel>){
        adapter = OrganizationAdapter(organizationList, { item: OrganizationModel -> partItemClicked(item) } )
        recyclerViewF!!.adapter = adapter
        progressBar.visibility = View.GONE
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    var total = visibleItemCount+ pastVisibleItemCount
                    pastVisibleItemCount =(recyclerView!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if(loading){
                        if((visibleItemCount+ pastVisibleItemCount) >= totalItemCount) {
                            progressBar.visibility = View.VISIBLE
                            loading = false
                            page++
                            organizationsViewModel.getOrganizatons( page++)
                        }
                    }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
    private fun partItemClicked(item : OrganizationModel) {

    }

}
