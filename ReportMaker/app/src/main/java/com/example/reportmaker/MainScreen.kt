package com.example.reportmaker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reportmaker.data.ScreenRoutes

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: ReportViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.Intro.route_name) {

        composable(ScreenRoutes.Intro.route_name) {
            IntroScreen(viewmodel = viewModel) {
                navController.navigate(ScreenRoutes.Marks1.route_name)
            }
        }

        composable(ScreenRoutes.Marks1.route_name) {
            val subjects1 = listOf("Maths", "English", "ICT")
            MarkScreen(
                subjects = subjects1,
                marks = viewModel.marks.value,
                errors = viewModel.markErrors.value,
                isNextEnabled = viewModel.isFirstMarkScreenValid.value,
                onMarkChange = { subject, value ->
                    viewModel.setMarks(subject, value, subjects1)
                },
                onNextClicked = {
                    navController.navigate(ScreenRoutes.Marks2.route_name)
                }
            )
        }

        composable(ScreenRoutes.Marks2.route_name) {
            val subjects2 = listOf("Physics", "Biology", "Chemistry")
            MarkScreen(
                subjects = subjects2,
                marks = viewModel.marks.value,
                errors = viewModel.markErrors.value,
                isNextEnabled = viewModel.isSecondMarkScreenValid.value,
                onMarkChange = { subject, value ->
                    viewModel.setMarks(subject, value, subjects2)
                },
                onNextClicked = {
                    viewModel.finalizeMarks()
                    navController.navigate(ScreenRoutes.Final.route_name)
                }
            )
        }

        composable(ScreenRoutes.Final.route_name) {
            FinalScreen(viewModel = viewModel,
                onRestartClicked = {
                    navController.navigate(ScreenRoutes.Intro.route_name) {
                        popUpTo(ScreenRoutes.Intro.route_name) { inclusive = true }
                        //basically if i press back after pressing restart , it will exit app , but if inc false , pressing back
                        //will reopen the data like if i had given name in previous run and restart , it will display those again
                    }
                }
            )
        }
    }
}