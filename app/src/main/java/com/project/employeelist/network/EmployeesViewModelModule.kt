package com.project.employeelist.network

import com.project.employeelist.repository.EmployeesRepository
import com.project.employeelist.viewmodel.EmployeeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EmployeesViewModelModule {

    @Provides
    @Singleton
    fun provideEmployeesServices(retrofit: Retrofit) = retrofit.create(EmployeeServices::class.java)

    @Provides
    @Singleton
    fun provideEmployeesRepository(employeeServices: EmployeeServices) = EmployeesRepository(employeeServices)

    @Provides
    @Singleton
    fun provideEmployeeViewModel(employeesRepository: EmployeesRepository) = EmployeeViewModel(employeesRepository)
}