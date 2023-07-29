package com.example.blocodenotas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blocodenotas.ui.theme.BlocoDeNotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlocoDeNotasTheme {
                DefaultPreview()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Main() {
    var anotacao by remember{ mutableStateOf("") }


    Scaffold(
        topBar = { // começo da topbar
            TopAppBar(
                backgroundColor = Color(0xFFFFC003),
            ){
                Text(text = "Bloco de Notas", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

        },

        floatingActionButton = {
            FloatingActionButton(onClick = {

            },
                backgroundColor = Color(0xFFFFC003),
                elevation = FloatingActionButtonDefaults.elevation(
                    8.dp
                )
            )
            {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_save_24) ,
                    contentDescription="Salvar"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),

        ) {
            TextField(
                value = anotacao,
                onValueChange = {
                anotacao = it
                },

                modifier = Modifier.fillMaxSize(),

                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedLabelColor = Color.White,
                    cursorColor = Color(0xFFFFC003),

                ) ,
                label = { Text(text = "Insira a sua anotação!") }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Main()
}