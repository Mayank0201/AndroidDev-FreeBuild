package com.example.a30daysapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.data.Movie
import com.example.a30daysapp.data.Movies
import com.example.a30daysapp.ui.theme._30DaysAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.a30daysapp.ui.theme.FincherColor
import com.example.a30daysapp.ui.theme.MindBendingColor
import com.example.a30daysapp.ui.theme.SatiricalColor
import com.example.a30daysapp.ui.theme.SpookyColor
import com.example.a30daysapp.ui.theme.TarantinoColor
import com.example.a30daysapp.ui.theme.ThrowbackColor
import com.example.a30daysapp.ui.theme.WholesomeColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DaysAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun MainApp(modifier: Modifier = Modifier) {
    MovieList(movieList=Movies,modifier=modifier)
    Text(text=stringResource(R.string.disclaimer))

}

@Composable
fun MovieList(movieList:List<Movie>,modifier: Modifier=Modifier){
    LazyColumn(modifier=modifier.fillMaxSize()){
        items(movieList){movie ->
            MovieCard(
                movie=movie,
                modifier=modifier
            )
        }
    }
}


@Composable
fun MovieCard(movie: Movie, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val screen = LocalConfiguration.current
    val sHeight = screen.screenHeightDp

    // Get theme-specific base color when expanded
    val themeColor = when (stringResource(id = movie.theme)) {
        stringResource(R.string.theme_monday) -> MindBendingColor
        stringResource(R.string.theme_tuesday) -> TarantinoColor
        stringResource(R.string.theme_wednesday) -> WholesomeColor
        stringResource(R.string.theme_thursday) -> ThrowbackColor
        stringResource(R.string.theme_friday) -> FincherColor
        stringResource(R.string.theme_saturday) -> SatiricalColor
        stringResource(R.string.theme_sunday) -> SpookyColor
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }

    val cardColor by animateColorAsState(
        targetValue = if (expanded) themeColor else MaterialTheme.colorScheme.primaryContainer,
        label = "Card color animation"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(text = stringResource(id = movie.day), style = MaterialTheme.typography.labelLarge)
            Text(text = stringResource(id = movie.movieName), style = MaterialTheme.typography.titleMedium)
            Text(text = stringResource(id = movie.theme), style = MaterialTheme.typography.labelSmall)

            Image(
                painter = painterResource(id = movie.imgId),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .height((sHeight * 0.3).dp)
            )

            MovieButton(expanded = expanded, onClick =  {
                expanded = !expanded
            })

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = movie.about), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


@Composable
private fun MovieButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription ="Expand button",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    _30DaysAppTheme {
//    }
//}