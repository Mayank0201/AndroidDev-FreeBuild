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
import coil.compose.rememberAsyncImagePainter
import com.example.bookshelf.model.BookShelfItem


@Composable
fun BookShelfScreen(
    modifier: Modifier = Modifier,
    viewModel: BookShelfViewModel
) {

    val state = viewModel.bookShelfState.value
    Text("Loaded: ${state.list.size} books")
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
                modifier = modifier.fillMaxSize(),
                bookList = state.list
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookShelfList(
    modifier: Modifier = Modifier,
    bookList: List<BookShelfItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(bookList) { book ->
            BookShelfItem(book = book)
        }
    }
}

@Composable
fun BookShelfItem(
    modifier: Modifier = Modifier,
    book: BookShelfItem
) {
    val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = book.volumeInfo.title,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}