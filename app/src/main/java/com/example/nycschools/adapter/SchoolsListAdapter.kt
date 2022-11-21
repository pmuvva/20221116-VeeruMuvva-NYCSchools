package com.example.nycschools.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.R
import com.example.nycschools.activity.SchoolDetailsActivity
import com.example.nycschools.data.NYCSchoolsResponse
import com.example.nycschools.util.Constants

class SchoolsListAdapter(var context: Context) : RecyclerView.Adapter<SchoolsListAdapter.ViewHolder>() {

    var dataList = emptyList<NYCSchoolsResponse>()

    internal fun setDataList(dataList: List<NYCSchoolsResponse>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView

        init {
            title = itemView.findViewById(R.id.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).
        inflate(R.layout.layout_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Involves populating data into the item through holder
        var data = dataList[position]
        holder.title.text = data.school_name
        holder.title.setOnClickListener {
            val intent = Intent(context, SchoolDetailsActivity::class.java)
            intent.putExtra(Constants.SCHOOL_DETAILS, data.dbn)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataList.size
}
