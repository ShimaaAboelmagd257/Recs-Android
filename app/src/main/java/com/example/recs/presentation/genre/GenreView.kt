package com.example.recs.presentation.genre

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp


@Composable
fun CardGrid(cards:List<CardContent>, columns:Int ) {

    val scrollState = rememberScrollState()
    VerticalAlignedGrid(
        columns = columns , modifier = Modifier.verticalScroll(scrollState)
    ) {
        cards.forEach { card ->
            CustomCard (
                modifier = Modifier.height(card.size).clickable {
                    card.route
                }
            ){
                Box(Modifier.fillMaxSize().padding(0.dp)) {
                    Image(
                        painter = card.image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize().height(card.size)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 5.dp)),
                        contentScale = ContentScale.FillBounds
                    )

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
