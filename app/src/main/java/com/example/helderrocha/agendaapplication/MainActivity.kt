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

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var itemsVMFactory: ViewModelFactory<OrganizationListViewModel>

    private val itemsViewModel by lazy {
        ViewModelProviders.of(this, itemsVMFactory)[OrganizationListViewModel::class.java]
    }

    protected val ItemsObserver = Observer<List<OrganizationModel>>(::onItemsFetched)
    private lateinit var adapter: OrganizationAdapter

    var layoutManager = LinearLayoutManager(this)


    var totalItemCount: Int = 0
    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var loading: Boolean = false
    var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        itemsViewModel.data.observe(this, ItemsObserver)
        itemsViewModel.getOrganizatons(page)
    }

    private fun onItemsFetched(organizationListFetched: List<OrganizationModel>?) {
        if (organizationListFetched != null) {
//            loading = true
            setUpdateAdapter(organizationListFetched)
        } else {
            Toast.makeText(this, "There is no", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setUpdateAdapter(organizationList: List<OrganizationModel>){
        adapter = OrganizationAdapter(organizationList, { item: OrganizationModel -> partItemClicked(item) } )
        recyclerView.adapter = adapter
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
                            itemsViewModel.getOrganizatons( page++)
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
//        val str1 = item.login.toLowerCase()
//        val str3 = item.name.toLowerCase()
//
//        val intent = Intent(this,PullRequestActivity::class.java)
//        intent.putExtra("name",str1)
//        intent.putExtra("repo",str3)
//        startActivity(intent)
    }
}
