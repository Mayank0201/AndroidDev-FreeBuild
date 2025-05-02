package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {

                    Art()

            }
        }
    }
}

@Composable
fun Art(modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val sHeight=configuration.screenHeightDp
    val sWidth=configuration.screenWidthDp

    var result by remember { mutableIntStateOf(0) }

    val img = when (result) {
        0 -> R.drawable.img_1
        1 -> R.drawable.img_2
        2 -> R.drawable.img_3
        else -> R.drawable.img_4
    }

    val title = when (result) {
        0 -> R.string.first_title
        1 -> R.string.second_title
        2 ->R.string.Third_title
        else -> R.string.Fourth_title
    }

    val name = when (result) {
        0 -> R.string.first_author
        1 -> R.string.second_author
        2->R.string.third_author
        else -> R.string.fourth_author
    }



    fun checkNext(r: Int): Int {
        return if (r > -1 && r < 3) {
            r + 1
        } else{
            0
        }
    }

    fun checkPrev(r:Int):Int{
        return if(r>=1 && r<=3){
            r-1

        } else{
            3
        }
    }


    Column(modifier=modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ) {

            Box(modifier=modifier.fillMaxWidth().
            border(8.dp,Color.DarkGray)){
            Image(
                painter = painterResource(img),
                contentDescription = null,
                modifier = modifier
                    .width((sWidth * 0.85).dp)
                    .height((sHeight*0.4).dp)
                    .padding(2.dp)
                    .align(Alignment.Center),
                //contentScale= ContentScale.Crop
            )
            }


        Spacer(modifier=modifier.height((0.03*sHeight).dp))


        Column(modifier=modifier.
        background(Color.LightGray)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,

        ){
            Text(text = stringResource(title))

            Text(text = stringResource(name))

        }

        Spacer(modifier=modifier.height((0.03*sHeight).dp))

        Row {
            Button(
                onClick = { result=checkPrev(result) }) {
                Text("Previous")
            }

            Button(
                onClick = { result=checkNext(result) }) {
                Text("Next")
            }
        }
        Spacer(modifier=modifier.height((0.3*sHeight).dp))

        Text(modifier=modifier,
                text=stringResource(R.string.footer),
        )

    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ArtSpaceTheme {
//        Art()
//    }
//}