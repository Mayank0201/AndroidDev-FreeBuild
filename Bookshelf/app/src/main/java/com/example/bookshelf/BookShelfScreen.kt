package com.example.bookshelf

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import coil.compose.rememberAsyncImagePainter
import com.example.bookshelf.model.BookShelfItem

@Composable
fun BookShelfScreen(modifier: Modifier = Modifier, viewModel: BookShelfViewModel) {
    val state = viewModel.bookShelfState.value
    val suggestions by viewModel.suggestions.collectAsState()

    var query by remember { mutableStateOf(TextFieldValue("")) }
    var showSuggestions by remember { mutableStateOf(true) }

    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                showSuggestions = true
                viewModel.fetchSuggestions(it.text)
            },
            label = { Text("Search Books") },
            modifier = modifier.fillMaxWidth()
                .padding(horizontal=6.dp)
        )

        if (showSuggestions && suggestions.isNotEmpty()) {
            Column(
                modifier = modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                suggestions.forEach { suggestion ->
                    TextButton(
                        onClick = {
                            query = TextFieldValue(suggestion)
                            showSuggestions = false
                            viewModel.fetchBooks(suggestion)
                        },
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(suggestion)
                    }
                }
            }
        }

        Button(
            onClick = {
                showSuggestions = false
                viewModel.fetchBooks(query.text)
            },
            modifier = modifier.align(Alignment.End)
                .padding(end = 8.dp)
        ) {
            Text("Search")
        }

        when {
            state.loading -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${state.error}")
                }
            }
            else -> {
                BookShelfList(
                    bookList = state.list,
                    modifier = modifier.weight(1f)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookShelfList(bookList: List<BookShelfItem>,modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(bookList) { book ->
            BookShelfCard(book = book)
        }
    }
}

@Composable
fun BookShelfCard(book: BookShelfItem,modifier: Modifier = Modifier) {
    val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier.fillMaxWidth()
            .height(240.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = book.volumeInfo.title,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
            Box(
                modifier = modifier.align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = book.volumeInfo.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = modifier.align(Alignment.BottomStart)
                        .padding(4.dp)
                )
            }
        }
    }
}