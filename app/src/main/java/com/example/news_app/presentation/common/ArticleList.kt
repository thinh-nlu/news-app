package com.example.news_app.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.news_app.domain.model.Article
import com.example.news_app.helper.Dimen
import com.example.news_app.helper.Dimen.ExtraSmallPadding2
import com.example.news_app.helper.Dimen.MediumPadding1

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = articles.size,
        ) {
            articles[it]?.let { article ->
                ArticleCard(article = article, onClick = { onClick(article) })
            }
        }
    }

}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(articles = articles)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimen.MediumPadding1),
            contentPadding = PaddingValues(all = Dimen.ExtraSmallPadding2)
        ) {
            items(
                count = articles.itemCount
            ) {
                articles[it]?.let { article ->
                    if (article != null) {
                        ArticleCard(article = article, onClick = {
                            onClick(article)
                        }
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimen.MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimen.MediumPadding1)
            )
        }
    }
}