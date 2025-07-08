package com.example.reportmaker

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ReportVMTest {
    private lateinit var viewModel: ReportViewModel

    @Before
    fun setup() {
        viewModel = ReportViewModel()
    }

    @Test
    fun setName_validName_shouldUpdateNameAndClearError() {
        //naming:functionName_condition_expectedResult
        viewModel.setName("Alice")
        assertEquals("Alice", viewModel.name.value)
        assertEquals("", viewModel.nameError.value)}

    @Test
    fun setId_validId_shouldUpdateIdAndClearError(){
        viewModel.setId("S.23.54")
        assertEquals("S.23.54",viewModel.id.value)
        assertEquals("",viewModel.idError.value)
    }

    @Test
    fun setId_invalidId_shouldCheckNull(){
        viewModel.setId("")
        assertEquals("Please enter a valid ID",viewModel.idError.value)
    }

    @Test
    fun validateCurrentScreen_validScreen_shouldGetValidScreen(){
        val validMarks=mapOf(
            "Maths" to "95",
            "English" to "88",
            "ICT" to "90",
            "Physics" to "85",
            "Biology" to "92",
            "Chemistry" to "87"
        )
        validMarks.forEach { (subject, value) ->
            viewModel.setMarks(subject, value, listOf(subject))
        }
        viewModel.finalizeMarks()

        val final = viewModel.finalMarks.value
        assertNotNull(final)

        assertEquals(viewModel.isFirstMarkScreenValid.value,true)
        assertEquals(viewModel.isSecondMarkScreenValid.value,true)
    }

    @Test
    fun validateCurrentScreen_invalidScreen1_shouldGetInvalidScreen1(){
        val validMarks=mapOf(
            "Maths" to "",
            "English" to "88",
            "ICT" to "90",
            "Physics" to "90",
            "Biology" to "90",
            "Chemistry" to "87"
        )
        validMarks.forEach { (subject, value) ->
            viewModel.setMarks(subject, value, listOf(subject))
        }
        viewModel.finalizeMarks()

        val final = viewModel.finalMarks.value
        assertNotNull(final)

        assertNotEquals(viewModel.isFirstMarkScreenValid.value,true)
    }

    @Test
    fun validateCurrentScreen_invalidScreen2_shouldGetInvalidScreen2(){
        val validMarks=mapOf(
            "Maths" to "12",
            "English" to "88",
            "ICT" to "90",
            "Physics" to "90",
            "Biology" to "",
            "Chemistry" to "87"
        )

        val subjects2 = listOf("Physics", "Biology", "Chemistry")

        subjects2.forEach {
            viewModel.setMarks(it, validMarks[it] ?: "", subjects2)
        }
        viewModel.finalizeMarks()

        assertEquals(false,viewModel.isSecondMarkScreenValid.value)
    }

    @Test
    fun finalizeMarks_validMarks_shouldUpdateMarksAndElseNull(){
        val validMarks = mapOf(
            "Maths" to "95",
            "English" to "88",
            "ICT" to "90",
            "Physics" to "85",
            "Biology" to "92",
            "Chemistry" to "87"
        )

        validMarks.forEach { (subject, value) ->
            viewModel.setMarks(subject, value, listOf(subject))
        }
        viewModel.finalizeMarks()

        val final = viewModel.finalMarks.value
        assertNotNull(final)

        assertEquals(95, final?.maths)
        assertEquals(88, final?.english)
        assertEquals(90, final?.ict)
        assertEquals(85, final?.physics)
        assertEquals(92, final?.biology)
        assertEquals(87, final?.chemistry)

    }

    @Test
    fun finalizeMarks_alphabetMarks_shouldNull(){
        val validMarks = mapOf(
            "Maths" to "95",
            "English" to "abc",
            "ICT" to "90",
            "Physics" to "85",
            "Biology" to "92",
            "Chemistry" to "87"
        )

        validMarks.forEach { (subject, value) ->
            viewModel.setMarks(subject, value, listOf(subject))
        }
        viewModel.finalizeMarks()

        val final = viewModel.finalMarks.value
        assertNotNull(final)

        assertEquals(95, final?.maths)
        assertEquals(0, final?.english)
        assertEquals(90, final?.ict)
        assertEquals(85, final?.physics)
        assertEquals(92, final?.biology)
        assertEquals(87, final?.chemistry)

    }

}