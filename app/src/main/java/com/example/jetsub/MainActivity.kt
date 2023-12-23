package com.example.jetsub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetsub.model.BottomBarItem
import com.example.jetsub.model.Menu
import com.example.jetsub.model.dummyBestSellerMenu
import com.example.jetsub.model.dummyCategory
import com.example.jetsub.model.dummyMenu
import com.example.jetsub.ui.theme.JetsubTheme
import com.example.jetsub.view.components.CategotyItem
import com.example.jetsub.view.components.HomeSection
import com.example.jetsub.view.components.MenuItem
import com.example.jetsub.view.components.MenuListItem
import com.example.jetsub.view.components.Search
import com.example.jetsub.view.route.Screen
import com.example.jetsub.viewmodel.CartViewModel
import com.example.jetsub.viewmodel.DetailViewModel
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinContext {
                JetsubTheme {
                    FashionismApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FashionismApp() {
    val navController: NavHostController = rememberNavController()
    val route by navController.currentBackStackEntryAsState()
    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = !route?.destination?.route.equals(Screen.Home.route) &&
                        !route?.destination?.route.equals(Screen.Profile.route) &&
                        !route?.destination?.route.equals(Screen.Cart.route)
            ) {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = route?.destination?.route?.contains(Screen.Home.route) ?: false ||
                        route?.destination?.route?.contains(Screen.Profile.route) ?: false ||
                        route?.destination?.route?.contains(Screen.Cart.route) ?: false
            ) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Banner(
                        onSearch = {
                            navController.navigate(Screen.Search.createRoute(it))
                        }
                    )
                    HomeSection(
                        title = stringResource(R.string.section_category),
                        content = {
                            CategoryRow(
                                onClick = {
                                    navController.navigate(Screen.Catalog.createRoute(it))
                                }
                            )
                        }
                    )
                    HomeSection(
                        stringResource(id = R.string.section_best_driver),
                        content = {
                            MenuRow(
                                listMenu = dummyMenu,
                                onClick = {
                                    navController.navigate(Screen.Detail.createRoute(it.image))
                                }
                            )
                        }
                    )
                    HomeSection(
                        title = stringResource(id = R.string.section_standings_menu),
                        content = {
                            MenuRow(
                                listMenu = dummyBestSellerMenu,
                                onClick = {
                                    navController.navigate(Screen.Detail.createRoute(it.image))
                                }
                            )
                        }
                    )
                }
            }

            composable(
                route = Screen.Search.route,
                arguments = listOf(
                    navArgument(name = "query") {
                        type = NavType.StringType
                    }
                )
            ) {
                val query = it.arguments?.getString("query") ?: ""
                val filteredMenu = dummyMenu.filter { it.title.contains(query, ignoreCase = true) }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(count = filteredMenu.size) {
                        MenuItem(
                            menu = filteredMenu[it],
                            onClick = {
                                navController.navigate(Screen.Detail.createRoute(it.image))
                            }
                        )
                    }
                }
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument(name = "image_id") {
                        type = NavType.IntType
                    }
                )
            ) {
                val imageID = it.arguments?.getInt("image_id") ?: 0
                val vm = koinInject<DetailViewModel>()
                LaunchedEffect(key1 = Unit) {
                    vm.getMenuByImage(imageID)
                }
                val count by vm.menuOnCart.collectAsState()
                val menu = dummyMenu.find { it.image == imageID }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    menu?.let {
                        Image(
                            painter = painterResource(id = menu.image),
                            contentDescription = menu.title,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Column(
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp
                                )
                        ) {
                            Text(
                                text = menu.title,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Black
                                )
                            )
                            Text(
                                text = stringResource(id = menu.category),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(text = menu.national, style = MaterialTheme.typography.titleMedium)
                        }
                        Text(
                            text = menu.desc,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                        Box(
                            contentAlignment = Alignment.BottomCenter,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                IconButton(
                                    onClick = {
                                        vm.takeFromMenu(menu)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowLeft,
                                        contentDescription = null
                                    )
                                }
                                Text(text = count.toString())
                                IconButton(
                                    onClick = {
                                        vm.addMenu(menu)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }

            composable(
                route = Screen.Catalog.route, arguments = listOf(
                    navArgument("catalog_id") {
                        type = NavType.IntType
                    }
                )
            ) {
                val catalogId = it.arguments?.getInt("catalog_id") ?: 0
                val filteredMenu = if (catalogId == R.string.category_all) {
                    dummyMenu
                } else {
                    dummyMenu.filter { it.category == catalogId }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(count = filteredMenu.size) {
                        MenuItem(
                            menu = filteredMenu[it],
                            onClick = {
                                navController.navigate(Screen.Detail.createRoute(it.image))
                            }
                        )
                    }
                }
            }

            composable(Screen.Profile.route) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(24.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rizan),
                        contentDescription = null,
                        modifier = Modifier
                            .size(125.dp)
                            .clip(CircleShape)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Person, contentDescription = null)
                            Text(
                                text = "Muhammad Rizan",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Email, contentDescription = null)
                            Text(
                                text = "rizanlos09@gmail.com",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }

            composable(Screen.Cart.route) {
                val vm = koinInject<CartViewModel>()
                val carts by vm.menus.collectAsState()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(carts.size) {
                        MenuListItem(
                            menu = carts[it],
                            onClick = {
                                navController.navigate(Screen.Detail.createRoute(it))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun FashionismAppPreview() {
    JetsubTheme {
        FashionismApp()
    }
}

@Composable
fun Banner(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.f1ban),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        Search(onSearch = onSearch)
    }
}

@Composable
fun CategoryRow(
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        ),
        modifier = modifier
    ) {
        items(dummyCategory.size) {
            CategotyItem(dummyCategory[it], onClick)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryRowPreview() {
    JetsubTheme {
        CategoryRow(
            onClick = {}
        )
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    onClick: (Menu) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu.size) { index ->
            MenuItem(menu = listMenu[index], onClick = onClick)
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val route by navController.currentBackStackEntryAsState()
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_cart),
                icon = Icons.Default.ShoppingCart
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            ),
        )
        navigationItems.map {
            val isActive =
                if (it.icon == Icons.Default.AccountCircle
                    && route?.destination?.route.equals(Screen.Profile.route)
                ) {
                    true
                } else if (it.icon == Icons.Default.Home &&
                    route?.destination?.route.equals(Screen.Home.route)
                ) {
                    true
                } else if (it.icon == Icons.Default.ShoppingCart &&
                    route?.destination?.route.equals(Screen.Cart.route)
                ) {
                    true
                } else {
                    false
                }
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = isActive,
                onClick = {
                    when (it.icon) {
                        Icons.Default.ShoppingCart -> {
                            navController.navigate(Screen.Cart.route) {
                                popUpTo(id = navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }

                        Icons.Default.AccountCircle -> {
                            navController.navigate(Screen.Profile.route) {
                                popUpTo(id = navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }

                        Icons.Default.Home -> {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(id = navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }
    }
}