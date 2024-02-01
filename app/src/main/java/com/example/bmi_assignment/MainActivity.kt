package com.example.bmi_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMI_AssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BMI()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMI_AssignmentTheme {
        BMI()
    }
}

@Composable
fun BMI(){
    var heightInput: String by remember { mutableStateOf("") }
    var weightInput: String by remember { mutableStateOf("") }
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toFloatOrNull() ?: 0.0f
    val bmi = if (height != 0.0f && weight != 0.0f) {
        weight / (height * height)
    } else {
        0.0f
    }

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Body mass index",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top =16.dp,bottom =16.dp)
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it.replace(',', '.') },
            label = { Text(text = "Height") },
            singleLine = true,
            keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it.replace(',', '.') },
            label = { Text(text = "Weight") },
            singleLine = true,
            keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = stringResource(R.string.result, String.format("%.2f", bmi).replace(',', '.')))
    }
}
