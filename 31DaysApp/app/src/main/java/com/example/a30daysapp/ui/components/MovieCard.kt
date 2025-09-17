package com.example.a30daysapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.data.Movie
import com.example.a30daysapp.ui.utils.getThemeStyle

@Composable
fun MovieCard(movie: Movie, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val screen = LocalConfiguration.current
    val sHeight = screen.screenHeightDp

    val style = getThemeStyle(movie.theme)

    val cardColor by animateColorAsState(
        targetValue = if (expanded) style.backgroundColor else Color.Black,
        label = "Card color animation"
    )

    val fontCardColor  = if (expanded) style.fontColor else Color.White

    val fontType=style.fontFamily

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
                fontFamily = style.fontFamily)

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
                modifier = modifier.fillMaxWidth()
                    .height((sHeight * 0.4).dp)
            )

            MovieButton(expanded = expanded, onClick =  {
                expanded = !expanded
            },
                color=fontCardColor)

            if (expanded) {
                Spacer(modifier = modifier.height(8.dp))
                Text(text = stringResource(id = movie.about),
                    color=fontCardColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = fontType)
            }
        }
    }
}
