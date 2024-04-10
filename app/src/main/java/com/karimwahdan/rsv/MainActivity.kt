package com.karimwahdan.rsv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.karimwahdan.rsv.buttons.IncrementButtons
import com.karimwahdan.rsv.ui.theme.RevitStoreViewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RevitStoreViewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")

                    val incBColors= ButtonDefaults.buttonColors()
                    val quantity= remember {
                        mutableIntStateOf(0)
                    }
                    IncrementButtons(
                        backgroundColor = Color.White,
                        quantityColor =Color.Black,
                        incrementButtonColors =incBColors,
                        incrementIcon = 1,
                        decrementButtonColors =incBColors,
                        decrementIcon =2,
                        quantity =quantity,
                        onIncrementClick = { /*TODO */ },
                        onDecrementClick = { /*TODO */ }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RevitStoreViewsTheme {
        Greeting("Android")
    }
}