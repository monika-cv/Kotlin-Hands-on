package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                ArtSpaceApp()
            }
        }
    }
}
@Preview
@Composable
fun ArtSpaceApp() {

    // State to track which artwork is displayed
    var currentArtwork by remember { mutableStateOf(1) }

    val artworks = listOf(
        Triple(R.drawable.art1, "Starry Sky", "Vincent van Gogh • 1889"),
        Triple(R.drawable.art2, "Peacock1", "Aria Devora • 2005"),
        Triple(R.drawable.art3, "Peacock2", "Celeste Rayne • 2014")
    )

    val (image, title, artistInfo) = artworks[currentArtwork - 1]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Box(
            modifier = Modifier
                .border(
                    width = 4.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = title,
                modifier = Modifier
                    .size(260.dp)
                    .padding(4.dp) // prevents image from touching the border directly
            )
        }



        // Artwork Details Section
        Surface(
            color = Color.LightGray.copy(alpha = 0.4f),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = artistInfo, fontSize = 18.sp)
            }
        }

        // Buttons Row
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(onClick = {
                currentArtwork =
                    if (currentArtwork == 1) artworks.size else currentArtwork - 1
            }) {
                Text("Previous")
            }

            Button(onClick = {
                currentArtwork =
                    if (currentArtwork == artworks.size) 1 else currentArtwork + 1
            }) {
                Text("Next")
            }
        }
    }
}
