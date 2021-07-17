package com.example.covitrack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covitrack.repository.Repository

class ViewModelFactory(private val repository: Repository):
ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoviViewModel(repository) as T
    }
}