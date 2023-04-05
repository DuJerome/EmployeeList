package com.project.employeelist.viewmodel

import androidx.lifecycle.ViewModel
import com.project.employeelist.model.Data
import com.project.employeelist.repository.EmployeesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository
): ViewModel() {
    fun getEmployees(): Single<Data> = employeesRepository.getEmployees()
    fun getEmployeesMalformed(): Single<Data> = employeesRepository.getEmployeesMalformed()
    fun getEmployeesEmpty(): Single<Data> = employeesRepository.getEmployeesEmpty()
}