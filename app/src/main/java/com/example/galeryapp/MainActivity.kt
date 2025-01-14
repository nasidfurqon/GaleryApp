package com.example.galeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeryapp.Model.Image
import com.example.galeryapp.Model.listOfImage
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
fun GaleryDisplay(image: Image,modifier :Modifier = Modifier) {
    var recentImage by remember{ mutableStateOf(listOfImage.get(listOfImage.indexOf(image)))}
    Column (modifier = modifier
        .fillMaxSize()){
        Spacer(
            Modifier
                .height(30.dp)
        )
        ImageText(
            ImageId = recentImage.imageId,
                ImageDesc = recentImage.name,
                NameId = recentImage.name,
                PlaceId = recentImage.place,
                YearId = recentImage.year,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
        )
//
        Row(modifier = Modifier.padding(top = 20.dp)
            .fillMaxWidth()
            , horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.width(130.dp),
                onClick = {if(listOfImage.indexOf(recentImage) > 0 ) recentImage = listOfImage.get((listOfImage.indexOf(recentImage))-1)
                else recentImage = listOfImage.get(listOfImage.size - 1)}) {
                Text(
                    text = "Previous"
                )
            }
            Spacer(
                Modifier.width(40.dp)
            )
            Button(modifier = Modifier.width(130.dp),
                onClick = { if(listOfImage.indexOf(recentImage) < listOfImage.size - 1) recentImage = listOfImage.get((listOfImage.indexOf(recentImage)) + 1)
                else recentImage = listOfImage.get(0)}) {
                Text(
                    text = "Next"
                )
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
fun ImageCard(displayed : Boolean, imageId: Image, onClick:() -> Unit,modifier: Modifier = Modifier){
    Card(modifier = modifier
        .clickable {onClick()},
        shape = RoundedCornerShape(30.dp)){
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(imageId.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(25.dp))
                    .aspectRatio(1f)
            )
            Column (modifier = Modifier
                .padding(start = 20.dp)){
                Text(
                    text = stringResource(imageId.place),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(imageId.year),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Composable
fun ListImage(image: List<Image>, modifier: Modifier = Modifier){
    var indexofImage by remember { mutableStateOf(1) }
    var displayed by remember { mutableStateOf(false) }
        LazyColumn(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            items(image) {
                if(displayed == false) {
                    ImageCard(
                        displayed = displayed,
                        onClick = { displayed = !displayed
                            indexofImage = image.indexOf(it)},
                        imageId = it,
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )
                }
                else{
                    Column {
                        Surface(modifier = Modifier
                            .padding(start = 40.dp, top = 30.dp),
                            shape = RoundedCornerShape(50.dp),
                            color = MaterialTheme.colorScheme.primary) {
                            IconButton(
                                onClick = { displayed = !displayed}) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .size(25.dp)

                                )
                            }
                        }
                        GaleryDisplay(image.get(indexofImage))
                    }
                }
            }

        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GaleryApp(){
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
        ListImage(listOfImage, modifier = Modifier
            .padding(paddingValue))
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