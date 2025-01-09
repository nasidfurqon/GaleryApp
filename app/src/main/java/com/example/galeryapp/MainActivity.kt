package com.example.galeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeryapp.ui.theme.GaleryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                GaleryAppPreview()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GaleryApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Galery Application",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
        ) { paddingValue ->
        ImageText(
            ImageId = R.drawable.img_20240414_152331,
            ImageDesc = R.string.name1,
            NameId = R.string.name1,
            PlaceId = R.string.place1,
            YearId = R.string.year1,
            { 1 },
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}
@Composable
fun ImageText(ImageId: Int,
              ImageDesc: Int,
              NameId: Int,
              PlaceId: Int,
              YearId: Int,
              currentImage: ()-> Unit,
              modifier: Modifier = Modifier
              ){

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        Surface(modifier = Modifier

            .shadow(elevation = 10.dp),
            border = BorderStroke(1.dp, color = Color.Gray)){
            Image(
                painter = painterResource(ImageId),
                contentDescription = stringResource(ImageDesc),
                modifier = Modifier
                    .width(300.dp)
                    .padding(30.dp)
            )
        }
        Spacer(
            Modifier.height(50.dp)
        )
        Surface(modifier = Modifier.align(Alignment.Start)
            .width(300.dp),
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.primaryContainer){
            Column(modifier = Modifier.padding(10.dp)){
                Text(
                    text = stringResource(PlaceId),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                )
                Text(
                    text = stringResource(NameId, stringResource(YearId)),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                )
            }
        }
        Row(modifier = Modifier.padding(top = 20.dp)){
            Button(modifier = Modifier.width(130.dp)
                , onClick = {}) {
                Text(
                    text = "Previous"
                )
            }
            Spacer(
                Modifier.width(40.dp)
            )
            Button(modifier = Modifier.width(130.dp),
                onClick = {}) {
                Text(
                    text = "Next"
                )
            }
        }
    }
}
@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun GaleryAppPreview() {
    GaleryAppTheme {
        GaleryApp()
    }
}