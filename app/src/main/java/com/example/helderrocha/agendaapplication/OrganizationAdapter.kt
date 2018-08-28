package com.example.helderrocha.agendaapplication


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.helderrocha.agendaapplication.model.OrganizationModel
import kotlinx.android.synthetic.main.organization_list_item.view.*

class OrganizationAdapter (var organizationModel: List<OrganizationModel>, val clickListener: (OrganizationModel) -> Unit) : RecyclerView.Adapter<OrganizationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: OrganizationModel, clickListener: (OrganizationModel) -> Unit) {
            itemView.textViewOrganization.text = item.name
            itemView.setOnClickListener { clickListener(item)}
            Glide.with(itemView)
                    .load(item.logo.thumb)
                    .into(itemView.imageViewOrganization)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.organization_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = organizationModel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        (holder as ViewHolder).bind(organizationModel[position], clickListener)
    }
}
