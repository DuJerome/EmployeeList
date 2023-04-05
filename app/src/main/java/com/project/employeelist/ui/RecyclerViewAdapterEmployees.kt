package com.project.employeelist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.employeelist.R
import com.project.employeelist.databinding.ListItemEmployeeBinding
import com.project.employeelist.model.Employee

class RecyclerViewAdapterEmployees constructor(
    private val context: Context?
) : ListAdapter<Employee, RecyclerViewAdapterEmployees.ViewHolder>(COMPARATOR) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ListItemEmployeeBinding? = DataBindingUtil.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_employee, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (checkIfSomeFieldIsNull(item) && currentList.size > 1) submitList(listOf())

        if (checkIfEveryFieldIsNull(item)){
            holder.binding?.labelNoEmployee?.visibility = View.VISIBLE
            holder.binding?.fullname?.visibility = View.INVISIBLE
            holder.binding?.email?.visibility = View.INVISIBLE
            holder.binding?.phoneNumber?.visibility = View.INVISIBLE
            holder.binding?.team?.visibility = View.INVISIBLE
            holder.binding?.employeeImage?.visibility = View.INVISIBLE
            holder.binding?.shift?.visibility = View.INVISIBLE
            return
        }
        holder.binding?.fullname?.text = item?.fullName
        holder.binding?.email?.text = item?.emailAddress
        holder.binding?.phoneNumber?.text = item?.phoneNumber
        holder.binding?.team?.text = item?.team
        holder.binding?.shift?.text = when (item?.employeeType) {
            "FULL_TIME" -> "Full Time"
            "PART_TIME" -> "Part Time"
            "CONTRACTOR" -> "Contractor"
            else -> "Unknown"
        }
        holder.binding?.employeeImage?.let { imageView ->
            Glide.with(context!!).load(item?.photoUrlSmall).into(imageView)
        }
    }

    private fun checkIfEveryFieldIsNull(item: Employee?): Boolean{
        return item?.employeeType == null &&
        item?.uuid == null &&
        item?.emailAddress == null &&
        item?.fullName == null &&
        item?.biography == null &&
        item?.phoneNumber == null &&
        item?.photoUrlLarge == null &&
        item?.photoUrlSmall == null &&
        item?.team == null
    }
    private fun checkIfSomeFieldIsNull(item: Employee?): Boolean{
        return item?.employeeType == null ||
                item.uuid == null ||
                item.emailAddress == null ||
                item.fullName == null ||
                item.biography == null ||
                item.phoneNumber == null ||
                item.photoUrlLarge == null ||
                item.photoUrlSmall == null ||
                item.team == null
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem.uuid == newItem.uuid
            }
        }
    }
}