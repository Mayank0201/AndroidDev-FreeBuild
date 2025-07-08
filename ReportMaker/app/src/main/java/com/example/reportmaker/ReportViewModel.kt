package com.example.reportmaker

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.example.reportmaker.data.SubjectMarks

class ReportViewModel : ViewModel() {

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _id = mutableStateOf("")
    val id: State<String> = _id

    private val _nameError = mutableStateOf("")
    val nameError: State<String> = _nameError

    private val _idError = mutableStateOf("")
    val idError: State<String> = _idError

    @SuppressLint("MutableCollectionMutableState")
    private val _marks = mutableStateOf(
        mutableMapOf(
            "Maths" to "",
            "English" to "",
            "ICT" to "",
            "Physics" to "",
            "Biology" to "",
            "Chemistry" to ""
        )
    )
    val marks: State<Map<String, String>> = _marks

    @SuppressLint("MutableCollectionMutableState")
    private val _markErrors = mutableStateOf(
        mutableMapOf<String, String>().apply {
            putAll(_marks.value.mapValues { "" })
        }
    )
    val markErrors: State<Map<String, String>> = _markErrors

    private val _isFirstMarkScreenValid = mutableStateOf(false)
    val isFirstMarkScreenValid: State<Boolean> = _isFirstMarkScreenValid

    private val _isSecondMarkScreenValid = mutableStateOf(false)
    val isSecondMarkScreenValid: State<Boolean> = _isSecondMarkScreenValid

    private val _finalMarks = mutableStateOf<SubjectMarks?>(null)
    val finalMarks: State<SubjectMarks?> = _finalMarks

    fun setName(value: String) {
        if (value.isNotBlank()) {
            _name.value = value
            _nameError.value = ""
        } else {
            _nameError.value = "Please enter a valid name"
        }
        validateCurrentScreen(emptyList())
    }

    fun setId(value: String) {
        if (value.isNotBlank()) {
            _id.value = value
            _idError.value = ""
        } else {
            _idError.value = "Please enter a valid ID"
        }
        validateCurrentScreen(emptyList())
    }

    fun setMarks(subject: String, value: String, currentSubjects: List<String>) {
        val updatedMarks = _marks.value.toMutableMap()
        updatedMarks[subject] = value
        _marks.value = updatedMarks

        val updatedErrors = _markErrors.value.toMutableMap()
        val intValue = value.toIntOrNull()
        updatedErrors[subject] = when {
            value.isBlank() -> "Enter marks for $subject"
            intValue == null -> "Enter a valid number"
            intValue < 0 || intValue > 100 -> "Marks must be between 0 and 100"
            else -> ""
        }
        _markErrors.value = updatedErrors

        val isValid = currentSubjects.all {
            val v = updatedMarks[it]?.toIntOrNull()
            v != null && v in 0..100
        }

        if (currentSubjects.contains("Maths")) {
            _isFirstMarkScreenValid.value = isValid
        } else {
            _isSecondMarkScreenValid.value = isValid
        }
    }

    fun validateCurrentScreen(subjectsOnThisScreen: List<String>) {
        val isValid = subjectsOnThisScreen.all { subject ->
            val value = _marks.value[subject]?.toIntOrNull()
            value != null && value in 0..100
        }

        val firstScreenSubjects = listOf("Maths", "English", "ICT")
        val secondScreenSubjects = listOf("Physics", "Biology", "Chemistry")

        when {
            subjectsOnThisScreen.containsAll(firstScreenSubjects) ->
                _isFirstMarkScreenValid.value = isValid
            subjectsOnThisScreen.containsAll(secondScreenSubjects) ->
                _isSecondMarkScreenValid.value = isValid
        }
    }

    fun finalizeMarks() {
        _finalMarks.value = SubjectMarks(
            maths = _marks.value["Maths"]?.toIntOrNull() ?: 0,
            english = _marks.value["English"]?.toIntOrNull() ?: 0,
            ict = _marks.value["ICT"]?.toIntOrNull() ?: 0,
            physics = _marks.value["Physics"]?.toIntOrNull() ?: 0,
            biology = _marks.value["Biology"]?.toIntOrNull() ?: 0,
            chemistry = _marks.value["Chemistry"]?.toIntOrNull() ?: 0
        )
    }

    fun getTotalMarks(): Int {
        return _marks.value.values.mapNotNull { it.toIntOrNull() }.sum()
    }

    fun getPassOrFail(): String {
        val total = _marks.value.values.mapNotNull { it.toIntOrNull() }.sum()
        return if (total >= 240) "Pass" else "Fail"
    }

    fun resetAll() {
        _name.value = ""
        _id.value = ""
        _nameError.value = ""
        _idError.value = ""

        val clearedMarks = mutableMapOf(
            "Maths" to "",
            "English" to "",
            "ICT" to "",
            "Physics" to "",
            "Biology" to "",
            "Chemistry" to ""
        )

        _marks.value = clearedMarks
        _markErrors.value = clearedMarks.mapValues { "" }.toMutableMap()

        _isFirstMarkScreenValid.value = false
        _isSecondMarkScreenValid.value = false
        _finalMarks.value = null
    }

}

