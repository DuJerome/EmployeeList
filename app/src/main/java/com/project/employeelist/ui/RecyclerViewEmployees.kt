package com.project.employeelist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.employeelist.R
import com.project.employeelist.databinding.ListItemEmployeeBinding
import com.project.employeelist.model.Employees

class RecyclerViewEmployees(
    private val context: Context?
): PagingDataAdapter<Employees, RecyclerViewEmployees.ViewHolder>(COMPARATOR) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: ListItemEmployeeBinding? = DataBindingUtil.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_employee, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding?.fullname?.text = item?.fullName
        holder.binding?.email?.text = item?.emailAddress
        holder.binding?.phoneNumber?.text = item?.phoneNumber
        holder.binding?.team?.text = item?.team
        holder.binding?.shift?.text = item?.employeeType
        holder.binding?.employeeImage?.let { imageView ->
            Glide.with(context!!)
                .load(item?.photoUrlSmall)
                .into(imageView)
        }
    }
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Employees>() {
            override fun areItemsTheSame(oldItem: Employees, newItem: Employees): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Employees, newItem: Employees): Boolean {
                return oldItem.uuid == newItem.uuid
            }
        }
    }
}