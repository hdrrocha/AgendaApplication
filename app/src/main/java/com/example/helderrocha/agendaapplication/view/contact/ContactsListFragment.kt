package com.example.helderrocha.agendaapplication.view.contact


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.helderrocha.agendaapplication.view.contact.adapter.PersonAdapter

import com.example.helderrocha.agendaapplication.R
import com.example.helderrocha.agendaapplication.model.PersonModel
import com.example.helderrocha.agendaapplication.view_model.PeopleListViewModel
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
class ContactsListFragment : Fragment() {

    @Inject
    lateinit var peopleVMFactory: ViewModelFactory<PeopleListViewModel>

    private val peopleViewModel by lazy {
        ViewModelProviders.of(this, peopleVMFactory)[PeopleListViewModel::class.java]
    }

    protected val ItemsObserver = Observer<List<PersonModel>>(::onItemsFetched)

    private lateinit var adapter: PersonAdapter
    var listPeople: MutableList<PersonModel> = mutableListOf()
    var layoutManager = LinearLayoutManager(context)

    var totalItemCount: Int = 0
    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var loading: Boolean = false
    var page: Int = 1

    var recyclerViewF: RecyclerView? = null
    lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.contacts_fragment_list, container, false)

        recyclerViewF = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerViewF!!.layoutManager = LinearLayoutManager(activity)
        recyclerViewF!!.setHasFixedSize(true)
        progressBar = view.findViewById(R.id.progressBarContacts)as ProgressBar


        peopleViewModel.data.observe(this, ItemsObserver)
        peopleViewModel.getPeople(1)
        return  view
    }


    private fun onItemsFetched(list: List<PersonModel>?) {
        if (list != null) {
            loading = true
            setUpdateAdapter(list)
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun setUpdateAdapter(peopleList: List<PersonModel>){
        if(peopleList.size > 0) {
            listPeople = peopleList  as MutableList<PersonModel>
            adapter = PersonAdapter(listPeople, { item: PersonModel -> partItemClicked(item) })
            recyclerViewF!!.adapter = adapter
            progressBar.visibility = View.GONE
        }else{
            listPeople.addAll(peopleList)
            var currentPosition =(recyclerViewF!!.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            listPeople.addAll(peopleList)
            adapter.notifyDataSetChanged()
            recyclerViewF!!.adapter!!.notifyDataSetChanged()
            recyclerViewF!!.scrollToPosition(currentPosition)
            progressBar.visibility = View.GONE
        }

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
                            peopleViewModel.getPeople( page++)
                        }
                    }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
    private fun partItemClicked(item: PersonModel) {
        val intent = Intent(this.context, PersonActivity::class.java)
        var idUser = item.id.toString()
        intent.putExtra("id", idUser)
        startActivity(intent)
    }

}
