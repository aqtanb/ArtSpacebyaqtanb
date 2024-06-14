package com.aktanberdi.artspacebyaqtanb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aktanberdi.artspacebyaqtanb.ui.theme.ArtSpaceByAqtanbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceByAqtanbTheme {
                ArtSpace()
            }
        }
    }
}

data class Quadruple<T1, T2, T3, T4>(
    val first: T1,
    val second: T2,
    val third: T3,
    val fourth: T4
)


@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    var currentArt by remember {mutableIntStateOf(1)}
    if (currentArt == 4) currentArt = 1
    if (currentArt == 0) currentArt = 3
    val (art, name, author, year) = when (currentArt) {
        1 -> {
            val art = R.drawable.first_art
            val name = R.string.first_art_name
            val author = R.string.first_art_author
            val year = R.string.first_art_year
            Quadruple(art, name, author, year)
        }
        2 -> {
            val art = R.drawable.second_art
            val name = R.string.second_art_name
            val author = R.string.second_art_author
            val year = R.string.second_art_year
            Quadruple(art, name, author, year)
        }
        else -> {
            val art = R.drawable.third_art
            val name = R.string.third_art_name
            val author = R.string.third_art_author
            val year = R.string.third_art_year
            Quadruple(art, name, author, year)
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = modifier
                .background(shape = RectangleShape, color = Color.White)
                .padding(40.dp)
                .shadow(elevation = 4.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(art),
                contentDescription = stringResource(R.string.art_description),
                modifier = modifier
                    .padding(40.dp)
                    .fillMaxWidth()
                    .size(400.dp)
            )
            Spacer(modifier = modifier.height(40.dp))
        }
        Box(
            modifier = modifier
                .background(color = colorResource(R.color.text_background))
        ) {
            Column (modifier = modifier.padding(20.dp)) {
                Text(
                    text = stringResource(name),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400
                )
                Spacer(modifier = modifier.height(5.dp))
                Text(
                    text = buildAnnotatedString {
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append(stringResource(author))
                        pop()
                        append(" " + stringResource(year))
                    },
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = modifier.height(80.dp))

        Row {
            Button(
                onClick = { currentArt -= 1 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.prussian_blue),
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.previous_button))
            }
            Spacer(modifier = modifier.width(40.dp))
            Button(
                onClick = { currentArt += 1 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.prussian_blue),
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.next_button))
            }
        }
        Spacer(modifier = modifier.height(100.dp))
    }

}
@Preview (showBackground = true, showSystemUi = true)
@Composable
fun ArtPreview() {
    ArtSpaceByAqtanbTheme {
        ArtSpace()
    }
}