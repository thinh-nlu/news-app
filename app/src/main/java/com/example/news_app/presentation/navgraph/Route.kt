package com.example.news_app.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object IntroScreen : Route("intro_screen")
    data object HomeScreen : Route("home_screen")
    data object SearchScreen : Route("search_screen")
    data object BookmarkScreen : Route("bookmark_screen")
    data object DetailScreen : Route("detail_screen")
    data object AppStartNavigation : Route("app_start_navigation")
    data object NewsNavigation : Route("news_navigation")
    data object NewsNavigatorScreen : Route("news_navigator_screen")
}