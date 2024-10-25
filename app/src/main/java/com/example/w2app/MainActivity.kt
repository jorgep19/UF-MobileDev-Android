package com.example.w2app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w2app.ui.theme.W2appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // start of Jetpack Compose implementation
        enableEdgeToEdge()
        setContent {
            W2appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    W2Form(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        // end of Jetpack Compose implementation

        // Uncomment this line to create the view using the XML
        // layoutImplOfW2Form();
    }

    /**
     * Jetpack Compose implementation of the W2Form view. This is the newer approach for
     * implementing Android views
     */
    @Composable
    fun W2Form(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.run {
                fillMaxSize().padding(horizontal = 15.dp, vertical = 24.dp)
            }
        ) {
            Text(
                text ="W-2 Form",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )


            var name by remember { mutableStateOf("") }
            var ssn by remember { mutableStateOf("") }
            var income by remember { mutableStateOf("") }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = ssn,
                onValueChange = { ssn = it },
                label = { Text("SSN") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = income,
                onValueChange = { income = it },
                label = { Text("Income") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { submitForm(name, ssn, income) },
                modifier = Modifier.fillMaxWidth()) {
                Text("Submit")
            }
        }
    }

    /**
     * XML Layout implementation for creating the w2 form ui by leverage the main.xml layout file.
     * This is the older approach for creating Android UI's
     */
    private fun layoutImplOfW2Form() {
        setContentView(R.layout.main);

        val nameInput: EditText = findViewById(R.id.name_input)
        val ssnInput: EditText = findViewById(R.id.ssn_input)
        val incomeInput: EditText = findViewById(R.id.income_input)

        val submitButton: Button = findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            submitForm(
                nameInput.text.toString(),
                ssnInput.text.toString(),
                incomeInput.text.toString()
            )
        }
    }

    /**
     * Display the values of our input field on a Toast. It expects to be called by the submit
     * button
     */
    fun submitForm(name: String, ssn: String, income: String) {
        val text = "Value for name=${name}" + "Value for ssn=$ssn" + "Value for income=$income";
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, text, duration) // in Activity
        toast.show()
    }


    /**
     * Preview method for testing our W2Form compose method on Android to test the out view set up.
     */
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        W2appTheme  {
            W2Form()
        }
    }
}