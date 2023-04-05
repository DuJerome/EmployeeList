package com.project.employeelist.network

import com.project.employeelist.model.Data
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface EmployeeServices {

    @GET("employees.json")
    fun getEmployees(): Single<Data>

    @GET("employees_malformed.json")
    fun getEmployeesMalformed(): Single<Data>

    @GET("employees_empty.json")
    fun getEmployeesEmpty(): Single<Data>
}