package com.example.news_app.presentation.intro.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.news_app.R
import com.example.news_app.helper.Dimen
import com.example.news_app.presentation.intro.Page

@Composable
fun IntroPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(Dimen.MediumPadding1))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = Dimen.MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = colorResource(id = R.color.display_small)
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = Dimen.MediumPadding2),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = colorResource(id = R.color.text_medium)
        )
    }
}