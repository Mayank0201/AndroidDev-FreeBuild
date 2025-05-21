package com.example.a30daysapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import com.example.a30daysapp.ui.theme._31DaysAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _31DaysAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp(modifier:Modifier=Modifier) {
    Scaffold(
        topBar = {
            TopAppBar()
        },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                MovieList(movieList = Movies)
            }
        }
    )
}

@Composable
fun MovieList(movieList:List<Movie>,modifier: Modifier=Modifier) {

    LazyColumn(modifier = modifier.fillMaxSize()
    ) {
        items(movieList) { movie ->
            MovieCard(movie = movie)
            Spacer(modifier=modifier.padding(8.dp))
        }

        item {
            Text(
                text = stringResource(R.string.disclaimer),
                modifier = modifier.padding(8.dp)
            )
        }
    }
}


@Composable
fun MovieCard(movie: Movie, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val screen = LocalConfiguration.current
    val sHeight = screen.screenHeightDp

    val themeColor = when (stringResource(id = movie.theme)) {
        stringResource(R.string.theme_monday) -> MindBendingColor
        stringResource(R.string.theme_tuesday) -> TarantinoColor
        stringResource(R.string.theme_wednesday) -> WholesomeColor
        stringResource(R.string.theme_thursday) -> ThrowbackColor
        stringResource(R.string.theme_friday) -> FincherColor
        stringResource(R.string.theme_saturday) -> SatiricalColor
        stringResource(R.string.theme_sunday) -> SpookyColor
        else -> MaterialTheme.colorScheme.primaryContainer
    }

    val fontColor = when (stringResource(id = movie.theme)) {
        stringResource(R.string.theme_monday) -> Color.White
        stringResource(R.string.theme_tuesday) -> Color.Black
        stringResource(R.string.theme_wednesday) -> Color.Black
        stringResource(R.string.theme_thursday) -> Color.White
        stringResource(R.string.theme_friday) -> Color.White
        stringResource(R.string.theme_saturday) -> Color.Black
        stringResource(R.string.theme_sunday) -> Color.White
        else -> Color.Black
    }

    val fontType = when (stringResource(id = movie.theme)) {
        stringResource(R.string.theme_monday) -> FontFamily(Font(R.font.ibmplexsans_regular))
        stringResource(R.string.theme_tuesday) -> FontFamily(Font(R.font.bebasneue_regular))
        stringResource(R.string.theme_wednesday) -> FontFamily(Font(R.font.anton_regular))
        stringResource(R.string.theme_thursday) -> FontFamily(Font(R.font.nunito_regular))
        stringResource(R.string.theme_friday) -> FontFamily(Font(R.font.josefinsans_regular))
        stringResource(R.string.theme_saturday) -> FontFamily(Font(R.font.fredoka_regular))
        stringResource(R.string.theme_sunday) -> FontFamily(Font(R.font.creepster_regular))
        else -> FontFamily(Font(R.font.anton_regular))
    }

    val cardColor by animateColorAsState(
        targetValue = if (expanded) themeColor else Color.Black,
        label = "Card color animation"
    )

    val fontCardColor by animateColorAsState(
        targetValue = if (expanded) fontColor else Color.White,
        label = "Card color animation"
    )

    Card(
        modifier = modifier.fillMaxWidth()
            .border(width=1.dp,color = Color.White,
                shape=MaterialTheme.shapes.small),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(modifier = modifier.padding(8.dp).animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ))){

            Text(text = stringResource(id = movie.day),
                color=fontCardColor,
                style = MaterialTheme.typography.labelMedium,
                fontFamily = fontType)

            Text(text = stringResource(id = movie.movieName),
                color=fontCardColor,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = fontType)

            Text(text = stringResource(id = movie.theme),
                color=fontCardColor,
                style = MaterialTheme.typography.labelMedium,
                fontFamily = fontType)

            Spacer(modifier=modifier.height(5.dp))

            Image(
                painter = painterResource(id = movie.imgId),
                contentDescription = stringResource(movie.movieName),
                modifier = modifier
                    .fillMaxWidth()
                    .height((sHeight * 0.4).dp)
            )

            MovieButton(expanded = expanded, onClick =  {
                expanded = !expanded
            },
                color=fontCardColor)

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = movie.about),
                    color=fontCardColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = fontType)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold

                )
            }

    )

}


@Composable
private fun MovieButton(
    expanded: Boolean,
    onClick: () -> Unit,
    color:Color,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription ="Expand button",
            tint =color
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    _30DaysAppTheme {
//    }
//}