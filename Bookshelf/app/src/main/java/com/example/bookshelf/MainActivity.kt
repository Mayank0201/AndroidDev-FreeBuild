package com.example.bookshelf

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.bookshelf.data.DefaultAppContainer
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    private val appContainer = DefaultAppContainer()
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = BookShelfViewModel(appContainer.bookshelfRepository)
            BookshelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BookShelfScreen(viewModel=viewModel,modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    BookshelfTheme {
//        Greeting("Android")
//    }
//}