package com.example.testuppita

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.testuppita.ui.theme.TestUppitaTheme

class MainActivity : ComponentActivity() {

    private lateinit var getActivityResult: ActivityResultLauncher<String>
    private var selectedImageUri: Uri? by mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerActivityResult()
        setContent {
            TestUppitaTheme {
                Card(modifier = Modifier.padding(16.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Cyan.copy(alpha = .1f))
                            .padding(32.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        selectedImageUri?.let { GlideCardImage(it) }
                        Greeting(name = "Itzel")
                        Greeting(name = "UPIITA")
                        Greeting(name = "Ing. Telematica")
                        Button(onClick = { openGallery() }) {
                            Greeting(name = "Selecciona Imagen")
                        }
                    }
                }
            }
        }
    }

    private fun openGallery() {
        getActivityResult.launch("image/*")
    }

    private fun registerActivityResult() {
        getActivityResult =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    selectedImageUri = it
                }
            }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideCardImage(uri: Uri) {
    GlideImage(
        model = uri,
        loading = placeholder(R.drawable.ic_launcher_foreground),
        failure = placeholder(R.drawable.ic_launcher_foreground),
        alignment = Alignment.Center,
        modifier = Modifier
            .size(200.dp)
            .background(shape = CircleShape, color = Color.Transparent),
        contentDescription = "",
        transition = CrossFade
    )
}

@Composable
fun ControlExampleView() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .weight(.5f)
                .fillMaxWidth()
                .background(Color.Magenta.copy(alpha = .1f))
        ) {
            Column(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxHeight()
                    .background(Color.Cyan.copy(alpha = .1f))
            ) {
                Greeting(name = "Hola")
            }
            Column(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxHeight()
                    .background(Color.Red.copy(alpha = .1f))
            ) {
                Greeting(name = "Hola")
            }
        }
        Column(
            modifier = Modifier
                .background(Color.Blue.copy(alpha = .1f))
                .fillMaxWidth()
                .weight(.5f)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .rotate(90f),
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = ""
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = ""
                )
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .rotate(180f),
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = ""
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .rotate(-90f),
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = ""
                )
            }
        }
    }
}