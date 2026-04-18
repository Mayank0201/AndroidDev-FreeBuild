package com.example.lemonade.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import android.content.SharedPreferences

class LemonadeViewModel(private val prefs:SharedPreferences) : ViewModel(){

    var result by mutableIntStateOf(prefs.getInt("result", 1))
        private set
    var count by mutableIntStateOf(prefs.getInt("count", 0))
        private set
    
    fun onRestart(){
        result = 1
        count = 0
        saveData()
    }

    fun onResultChange(newResult: Int){
        result = newResult
        saveData()
    }

    fun incrementCount(){
        count++
        saveData()
    }

    fun saveData(){
        prefs.edit().apply{
            putInt("result", result)
            putInt("count", count)
            apply()
        }
    }

}
