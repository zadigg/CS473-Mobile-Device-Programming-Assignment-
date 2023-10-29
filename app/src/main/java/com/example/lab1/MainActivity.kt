package com.example.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1.ui.theme.Lab1Theme
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DinnerDeciderApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DinnerDeciderApp() {
    var selectedFood by remember { mutableStateOf("Random Food Will Appear Here") }
    var newFood by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(id = R.drawable.dd_logo),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }

        // Display the selected food
        Text(
            text = selectedFood,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Create an input that takes something
        TextField(
            value = newFood,
            onValueChange = { newFood = it },
            label = { Text("Enter a new food") },
            modifier = Modifier.padding(16.dp)
        )

        // ADD FOOD button
        Button(
            onClick = {
                if (newFood.isNotBlank()) {
                    foodList.add(newFood)
                    newFood = ""
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("ADD FOOD")
        }

        // DECIDE button
        Button(
            onClick = { selectedFood = decideOnFood() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("DECIDE")
        }
    }
}

private fun <E> List<E>.add(newFood: E) {
    this.toMutableList().add(newFood)

}

//create a food list of "Hamburger", "Pizza", "Mexican", "American", "Chinese and also can pass a new one from input
private val foodList = mutableListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese")

fun decideOnFood(): String {
    val randomIndex = (0 until foodList.size).random()
    return foodList[randomIndex]
}
