package com.example.blocodenotas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blocodenotas.datastore.Anotacao
import com.example.blocodenotas.ui.theme.BlocoDeNotasTheme
import kotlinx.coroutines.launch

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

    val context = LocalContext.current
    val storeAnotacao = Anotacao(context)
    val anotacaoSalva = storeAnotacao.getAnotacao.collectAsState(initial = "")
    val scope = rememberCoroutineScope()
    var anotacao by remember{ mutableStateOf("") }

    anotacao = anotacaoSalva.value.toString()

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
                scope.launch {
                    storeAnotacao.salvarAnotacao(anotacao)
                    Toast.makeText(context,"Anotação salva.",Toast.LENGTH_SHORT).show()
                }
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