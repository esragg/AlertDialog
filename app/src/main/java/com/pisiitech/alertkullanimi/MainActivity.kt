package com.pisiitech.alertkullanimi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pisiitech.alertkullanimi.ui.theme.AlertKullanimiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlertKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sayfa() {
    val acilisKontrolVarsayilan = remember {mutableStateOf(false) }
    val acilisKontrolOzel = remember {mutableStateOf(false) }
    val tf = remember {mutableStateOf("") }
     Column(modifier = Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.SpaceEvenly,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Button(onClick = {
            acilisKontrolVarsayilan.value = true
         }) {
             Text(text = "Varsayilan")
         }

         if(acilisKontrolVarsayilan.value) {
             AlertDialog(
                 onDismissRequest = {acilisKontrolVarsayilan.value = false}, //boslugu secersem
                 title = { Text(text = "Baslik")},
                 text = { Text(text = "Mesaj")},
                 confirmButton = {
                    Text(
                        text = "Tamam",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            Log.e("Alert","Tamam secildi")
                            acilisKontrolVarsayilan.value = false
                        })
                 },
                 dismissButton = {
                     Text(
                         text = "Iptal",
                         color = Color.Blue,
                         fontWeight = FontWeight.Bold,
                         modifier = Modifier.clickable {
                             Log.e("Alert","Iptal secildi")
                             acilisKontrolVarsayilan.value = false
                         })
                 }
             )
         }
         Button(onClick = {
            acilisKontrolOzel.value = true
         }) {
             Text(text = "Ozel")
         }
         if(acilisKontrolOzel.value) {
             AlertDialog(
                 onDismissRequest = {acilisKontrolOzel.value = false}, //boslugu secersem
                 title = { Text(text = "Baslik",color = Color.White)},
                 text = {
                        TextField(
                            value = tf.value,
                            onValueChange = {tf.value = it },
                            label = { Text(text = "Veri")}
                        )
                 },
                 confirmButton = {
                     Text(
                         text = "Tamam",
                         color = Color.Red,
                         fontWeight = FontWeight.Bold,
                         modifier = Modifier.padding(all = 10.dp).clickable {
                             Log.e("Alert","Tamam secildi : ${tf.value}")
                             acilisKontrolOzel.value = false
                             tf.value = ""
                         })
                 },
                 dismissButton = {
                     Text(
                         text = "Iptal",
                         color = Color.Red,
                         fontWeight = FontWeight.Bold,
                         modifier = Modifier.padding(all = 10.dp).clickable {
                             Log.e("Alert","Iptal secildi")
                             acilisKontrolOzel.value = false
                             tf.value = ""
                         })
                 },
                 containerColor = Color.LightGray
             )
         }
     }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlertKullanimiTheme {
        Sayfa()
    }
}