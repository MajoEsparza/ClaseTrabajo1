package com.example.clasetrabajo1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clasetrabajo1.R

@Composable
fun TestScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {


        Column() {
            Column(
                modifier = Modifier
                    .padding(2.dp, 10.dp, 0.dp, 10.dp)
            ) {
                Text("Test Screen")
            }
            TextComposable("Karime")
            TextComposable()
            TextComposable()
            TextComposable()
        }
        Row() {
            TextComposable()
            TextComposable()
            TextComposable()
            TextComposable()
        }
        Column() {
            ModifierExample1()
            ModifierExample2()
            //Comment if the screen ran out of space
            ModifierExample3()
            ModifierExample4()
            CustomText()
            Picture()
        }
        Button(
            onClick = { navController.navigate("mainMenu")}
        )
        {
            Text("Return to Main Menu")
        }
        Button(
            onClick = { navController.navigate("homeScreen")}
        )
        {
            Text("Go to Home Screen")
        }

    }

}

@Composable
fun TextComposable(name:String = "Empty"){
    Text(text = "Welcome")
    Text(name)
}

@Preview(showBackground = true)
@Composable
fun ModifierExample1(){
    Column(
        modifier = Modifier
            .padding(40.dp, 30.dp, 20.dp, 10.dp)
    ){
        Text("Hello World")
    }
}
@Preview(showBackground = true)
@Composable
fun ModifierExample2(){
    Column(
        modifier = Modifier
            //Left, Top, Right, Bottom
            .padding(40.dp, 30.dp, 20.dp, 10.dp)
            //use 100% of the device width, height, or both
            .fillMaxWidth()
            .clickable(onClick = { clickAction() })
    ){
        Text("Hello World")
    }
}
fun clickAction(){
    println("Column clicked")
}

@Preview(showBackground = true)
@Composable
fun ModifierExample3(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color.Yellow)
            .border(width = 2.dp, color = Color.DarkGray)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        //Use all of the available space in the screen evenly
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        TextComposable("1")
        TextComposable("2")
        TextComposable("3")
        TextComposable("4")

    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample4(){
    Box(
        modifier = Modifier
            .background(Color.Magenta)
            .padding(10.dp)
            .height(250.dp)
            .width(250.dp)
    ){
        //Text on top
        Text("1", Modifier.align(TopStart))
        Text("2", Modifier.align(Alignment.TopCenter))
        Text("3", Modifier.align(Alignment.TopEnd))
        //Text on center
        Text("4", Modifier.align(Alignment.CenterStart))
        Text("5", Modifier.align(Alignment.Center))
        Text("6", Modifier.align(Alignment.CenterEnd))
        //Text on bottom
        Text("7", Modifier.align(Alignment.BottomStart))
        Text("8", Modifier.align(Alignment.BottomCenter))
        Text("9", Modifier.align(Alignment.BottomEnd))
    }
}

@Preview(showBackground = true)
@Composable
fun CustomText(){
    Column(){
        //usage of strings.xml to save a sample String
        Text(
            stringResource(R.string.example_text),
            color = colorResource(R.color.purple_500),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors = listOf(Color.Red, Color.Blue, Color.Green, colorResource(R.color.black))
        Text(
            stringResource(R.string.example_text),
            style = TextStyle(brush = Brush.horizontalGradient(colors = gradientColors))
        )
    }
}
@Preview(showBackground = true)
@Composable
fun Picture(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .height(300.dp)
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(R.drawable.android1),
            contentDescription = "Logo Android",
            contentScale = ContentScale.Crop
        )
    }
}