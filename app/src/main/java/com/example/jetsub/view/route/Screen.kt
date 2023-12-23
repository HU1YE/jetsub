package com.example.jetsub.view.route

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search/{query}") {
        fun createRoute(query: String): String {
            return this.route.replace(
                oldValue = "{query}",
                newValue = query
            )
        }
    }
    object Detail: Screen("detail/{image_id}") {
        fun createRoute(imageId: Int): String {
            return this.route.replace(
                oldValue = "{image_id}",
                newValue = imageId.toString()
            )
        }
    }
    object Catalog: Screen("catalog/{catalog_id}") {
        fun createRoute(catalogId: Int): String {
            return this.route.replace(
                oldValue = "{catalog_id}",
                newValue = catalogId.toString()
            )
        }
    }
    object Profile: Screen("profile")
    object Cart: Screen("cart")
}