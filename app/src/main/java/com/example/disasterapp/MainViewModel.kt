package com.example.disasterapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disasterapp.data.DataGeometries
import com.example.disasterapp.data.Report
import kotlinx.coroutines.launch

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
    EMPTY
}

class MainViewModel: ViewModel() {
    private val _disasterList = MutableLiveData<List<DataGeometries>>()
    val disasterList: LiveData<List<DataGeometries>> = _disasterList

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    fun fetchData() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                _disasterList.value = Report.retrofitService.getDisasterData(null,null,null).result.objects.output.geometries
                _status.value = Status.SUCCESS
            } catch (
                e:Exception
            ) {
                _status.value = Status.ERROR
            }
        }
    }
}