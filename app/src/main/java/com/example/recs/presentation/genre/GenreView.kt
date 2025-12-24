package com.example.recs.presentation.genre

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.mini_netflix.R
import com.example.recs.data.model.Genre
import com.example.recs.utility.Const


@Composable
fun GenreView(
    viewModel: GenreViewModel = hiltViewModel(),
    onGenreClicked:(genreId:Int)-> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getGenres()
    }
    when(state){

        is GenreStatus.Success -> {
            val genres = (state as GenreStatus.Success).data.genres
            CardGrid(
                genres = genres,
                columns = 2,
                onGenreClicked = onGenreClicked
            )
        }
        is GenreStatus.Error -> {
            Image(
                painter = painterResource(id = R.drawable.utl),
                contentDescription = "ERROR",
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Crop

            )
            Text(
                text = "Error loading movies",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
            Log.e(Const.APP_LOGS,"Error in Genre")
        }
        GenreStatus.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp),
                    color = Color.Black)
                Log.w(Const.APP_LOGS,"LOADING in Genre View")
            }

        }
    }

}


/*fun List<Genre>.toCardContentList(): List<CardContent> {
    return map { genre ->
        CardContent(
            title = genre.name,
            size = 180.dp,
            image = R.drawable.ic_launcher_foreground,
            route = NavigationRoute.Home.route
        )
    }
}*/
@Composable
fun CardGrid(genres: List<Genre>, columns:Int, onGenreClicked:(Int) -> Unit ) {

    val scrollState = rememberScrollState()
    VerticalAlignedGrid(
        columns = columns , modifier = Modifier.verticalScroll(scrollState)
    ) {
        genres.forEach { card ->
            CustomCard (
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onGenreClicked(card.id)
                    }
            ){
                Box(Modifier
                    .fillMaxSize()
                    .padding(5.dp))
                {
                    Column (modifier =  Modifier.fillMaxSize().padding(5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(R.drawable.genre_filled),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 5.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(text = card.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                   }
                }
            }

        }
    }


}

@Composable
fun VerticalAlignedGrid(columns:Int , modifier: Modifier, content:@Composable () ->Unit) {
    Layout(
        content = content,
        modifier = modifier
    ) { measures, constraints ->
        val cardWidth = constraints.maxWidth / columns
        val cardConstraints = constraints.copy(maxWidth = cardWidth)

        val placeable = measures.map { m ->
            m.measure(cardConstraints)
        }

        val columnHeights = IntArray(columns) { 0 }

        placeable.forEachIndexed { index , place ->
            val column = index % columns
            columnHeights[column] += place.height


        }

        val height = columnHeights.maxOrNull() ?: constraints.minHeight

        layout(constraints.maxWidth, height) {
            val columnYOffsets = IntArray(columns) { 0 }

            placeable.forEachIndexed { index, placeable ->
                val column = index % columns
                val x = column * cardWidth
                val y = columnYOffsets[column]
                placeable.placeRelative(x, y)
                columnYOffsets[column] += placeable.height
            }
        }
    }

}

@Composable
fun CustomCard(
    modifier: Modifier =Modifier,
    content : @Composable () -> Unit
) {
    Card(
        modifier = modifier.padding(7.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,   // normal state
            pressedElevation = 8.dp,   // when pressed
            focusedElevation = 6.dp,   // when focused
            hoveredElevation = 6.dp,   // when hovered
            draggedElevation = 10.dp,  // when dragged
            disabledElevation = 0.dp   // when disabled
        ),
        shape = RoundedCornerShape(7.dp)
    ){
        content()
    }
}
