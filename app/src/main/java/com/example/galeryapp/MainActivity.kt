package com.example.galeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun GaleryDisplay() {
    var recentImage by remember{ mutableStateOf(1)}
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Gallery Application",
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
        Column (modifier = Modifier
            .padding(paddingValue)
            .fillMaxSize()){
            Surface(modifier = Modifier
                .padding(start = 37.dp, top = 30.dp),
                shape = RoundedCornerShape(50.dp),
                color = MaterialTheme.colorScheme.primary) {
                IconButton(
                    onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(25.dp)

                    )
                }
            }
            Spacer(
                Modifier
                    .height(50.dp)
            )
            when (recentImage) {
                1 -> ImageText(

                    ImageId = R.drawable.place1,
                    ImageDesc = R.string.name1,
                    NameId = R.string.name1,
                    PlaceId = R.string.place1,
                    YearId = R.string.year1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                )

                2 -> ImageText(

                    ImageId = R.drawable.place2,
                    ImageDesc = R.string.name2,
                    NameId = R.string.name2,
                    PlaceId = R.string.place2,
                    YearId = R.string.year2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                )

                3 -> ImageText(

                    ImageId = R.drawable.place3,
                    ImageDesc = R.string.name3,
                    NameId = R.string.name3,
                    PlaceId = R.string.place3,
                    YearId = R.string.year3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                )
            }
            Row(modifier = Modifier.padding(top = 20.dp)
                .fillMaxWidth()
                , horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier.width(130.dp),
                    onClick = { if(recentImage > 0 ) recentImage--
                    else recentImage = 3}) {
                    Text(
                        text = "Previous"
                    )
                }
                Spacer(
                    Modifier.width(40.dp)
                )
                Button(modifier = Modifier.width(130.dp),
                    onClick = { if (recentImage < 3) recentImage++
                    else recentImage = 1}) {
                    Text(
                        text = "Next"
                    )
                }
            }
        }
    }
}
@Composable
fun ImageText(ImageId: Int,
              ImageDesc: Int,
              NameId: Int,
              PlaceId: Int,
              YearId: Int,
              modifier: Modifier = Modifier
              ){

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        Surface(modifier = Modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.secondary),
            shape = RoundedCornerShape(16.dp)){
            Image(
                painter = painterResource(ImageId),
                contentDescription = stringResource(ImageDesc),
                modifier = Modifier
                    .width(300.dp)
                    .padding(30.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
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

    }
}

@Composable
fun ListImage(){

}

@Composable
fun GaleryApp(){
    ListImage()
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun GaleryAppPreview() {
    GaleryAppTheme {
        GaleryApp()
    }
}