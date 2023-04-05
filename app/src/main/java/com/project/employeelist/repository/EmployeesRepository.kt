package com.project.employeelist.repository

import com.project.employeelist.model.Data
import com.project.employeelist.network.EmployeeServices
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeesRepository @Inject constructor(
    private val employeeServices: EmployeeServices
){
    fun getEmployees(): Single<Data> = employeeServices.getEmployees()
    fun getEmployeesMalformed(): Single<Data> = employeeServices.getEmployeesMalformed()
    fun getEmployeesEmpty(): Single<Data> = employeeServices.getEmployeesEmpty()
}