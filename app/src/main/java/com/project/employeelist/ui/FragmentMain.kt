package com.project.employeelist.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.employeelist.R
import com.project.employeelist.databinding.FragmentMainBinding
import com.project.employeelist.model.Employee
import com.project.employeelist.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMain: Fragment(R.layout.fragment_main) {
    private val viewModel: EmployeeViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapterEmployees

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerViewAdapterEmployees(context)
        binding = DataBindingUtil.bind(view)!!
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        var list = viewModel.getEmployees().blockingGet().employees
        if (list.isEmpty()){
            list = mutableListOf(
                Employee(null, null, null, null, null,
                null, null, null, null)
            )
            adapter.submitList(list)
        }else adapter.submitList(list)
    }
}