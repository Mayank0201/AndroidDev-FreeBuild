package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                    Lemonade()

            }
        }
    }
}

@Composable
fun Lemonade() {
    Img(modifier=Modifier
        )
}

@Composable
fun Img(modifier: Modifier){


    var result by remember{mutableIntStateOf(1)}
    val img = when(result) {//since it is inside composable , whole code composed again , so we can use val
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3-> R.drawable.lemon_drink
        else-> R.drawable.lemon_restart

    }
    val t=when(result){
            1->R.string.first
            2->R.string.second
            3->R.string.third
            else->R.string.fourth

    }

    Column(
        modifier = modifier.background(color=Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier=modifier.height(LocalConfiguration.current.screenHeightDp.dp*0.02f))

        Text(text= stringResource(R.string.app_name),Modifier.background(Color.Yellow).fillMaxWidth(),
                fontSize=16.sp,
                textAlign = TextAlign.Start)

            Spacer(modifier=modifier.height(LocalConfiguration.current.screenHeightDp.dp*0.35f))

            Image(
                painter = painterResource(img),
                contentDescription = stringResource(R.string.img1),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(3.dp,Color(red=105,green=205,blue=216)
                        , RoundedCornerShape(16.dp))
                    .clickable {
                    if(result==1){
                        result += 1
                    }
                    else if(result==2) {

                            val a = (0..3).random()
                            if(a==1){
                            result=result+a
                            }

                    }

                    else if(result==4){
                        result=1
                    }
                    else{
                        result+=1
                    }
                }
            )

        Spacer(modifier=modifier.height(16.dp))

    Text(text = stringResource(t),
        modifier=modifier.background(color=Color.White)
            .align(Alignment.CenterHorizontally)
                ,fontSize=16.sp)

    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LemonadeTheme {
//        Lemonade()
//    }
//}