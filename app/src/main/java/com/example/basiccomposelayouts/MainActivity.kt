package com.example.basiccomposelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccomposelayouts.ui.theme.BasicComposeLayoutsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            BasicLayoutApp(windowSize = windowSizeClass)
        }
    }
}



//<-------------The searchBar composable---------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar( modifier: Modifier = Modifier ) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.the_search_icon)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = stringResource(R.string.search))
        }
    )
}

@Preview(name = "SearchBar Preview", showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar()
}



//<---------------Align your body composable-----------
@Composable
fun AlignYourBody(
    modifier: Modifier = Modifier,
    @DrawableRes bodyImage: Int,
    @StringRes bodyText: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(bodyImage),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(shape = CircleShape)
        )
        Text(
            text = stringResource(bodyText),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .paddingFromBaseline(
                    top = 24.dp,
                    bottom = 8.dp
                )
        )
    }
}

@Preview(name = "AlignYourBodyPreview", showBackground = true)
@Composable
fun AlignYourBodyPreview() {
    AlignYourBody(
        bodyImage = R.mipmap.pasport_photo,
        bodyText = R.string.inversions
    )
}



//<------------------Favorite collection card composable------------
@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier,
    @DrawableRes bodyImage: Int,
    @StringRes bodyText: Int
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier
                .width(255.dp)
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = bodyImage),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = stringResource(id = bodyText),
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(name = "FavoriteCollectionCardPreview", showBackground = true)
@Composable
fun FavoriteCollectionCardPreview() {
    FavoriteCollectionCard(
        bodyImage = R.mipmap.pasport_photo,
        bodyText = R.string.nature_meditations
    )
}


//<-------------------The FavoriteCollectionCard  grid-----------
data class FavoriteCollectionCardItems(
    @DrawableRes val imageResource: Int,
    @StringRes val textResource: Int
)

@Composable
fun FavoriteCollectionCardGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        modifier = modifier.height(168.dp),
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ){
        val favoriteCollectionCardData = listOf(
            FavoriteCollectionCardItems(imageResource = R.mipmap.pasport_photo, textResource = R.string.nature_meditations),
            FavoriteCollectionCardItems(imageResource = R.mipmap.pasport_photo, textResource = R.string.nature_meditations),
            FavoriteCollectionCardItems(imageResource = R.mipmap.pasport_photo, textResource = R.string.nature_meditations),
            FavoriteCollectionCardItems(imageResource = R.mipmap.pasport_photo, textResource = R.string.nature_meditations),
            FavoriteCollectionCardItems(imageResource = R.mipmap.pasport_photo, textResource = R.string.nature_meditations),
            FavoriteCollectionCardItems(imageResource = R.mipmap.pasport_photo, textResource = R.string.nature_meditations),
        )

        items( favoriteCollectionCardData ){item ->
            FavoriteCollectionCard(bodyImage = item.imageResource, bodyText = item.textResource)
        }
    }
}

@Preview(showBackground = true, name = "FavoriteCollectionCardGrid Preview")
@Composable
fun FavoriteCollectionCardGridPreview() {
//    FavoriteCollectionCardGrid()
}




//<-------------------Align Your Body Lazy row composable----------------
data class AlignYourBodyItem (
    @DrawableRes val imageResource: Int,
    @StringRes val textResource: Int
)

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        val alignYourBodyData = listOf(
            AlignYourBodyItem(imageResource = R.mipmap.pasport_photo, textResource = R.string.inversions),
            AlignYourBodyItem(imageResource = R.mipmap.pasport_photo, textResource = R.string.inversions),
            AlignYourBodyItem(imageResource = R.mipmap.pasport_photo, textResource = R.string.inversions),
            AlignYourBodyItem(imageResource = R.mipmap.pasport_photo, textResource = R.string.inversions),
            AlignYourBodyItem(imageResource = R.mipmap.pasport_photo, textResource = R.string.inversions),
            AlignYourBodyItem(imageResource = R.mipmap.pasport_photo, textResource = R.string.inversions),
            AlignYourBodyItem(imageResource = R.mipmap.pasport_photo, textResource = R.string.inversions),
        )
        items( alignYourBodyData ) { item ->
            AlignYourBody(bodyImage = item.imageResource, bodyText = item.textResource)
        }
    }
}

@Preview(name = "AlignYourBodyRowPreview", showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
    AlignYourBodyRow()
}



//<-------------The home section composable---------------
@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(id = title),
            modifier = Modifier
                .paddingFromBaseline(
                    top = 40.dp,
                    bottom = 16.dp
                )
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        content()
    }
}

@Preview(name = "HomeSectionPreview", showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    BasicComposeLayoutsTheme {
        HomeSection( title = R.string.align_your_body ) {
            AlignYourBodyRow()
        }
    }
}

//<----------The HomeScreen composable--------------
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar()
        HomeSection( title = R.string.align_your_body ) {
            AlignYourBodyRow()
        }
        HomeSection( title = R.string.favorite_collections ) {
            FavoriteCollectionCardGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, name = "HomeScreenPreview")
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}



//<----------BasicLayoutBottomNavigationBar composable-----------
@Composable
private fun BasicLayoutBottomNavigationBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {

        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.home))
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.profile))
            }
        )

    }
}

@Preview( name = "BasicLayoutBottomNavigationBar", showBackground = true )
@Composable
fun BasicLayoutBottomNavigationBarPreview() {
    BasicLayoutBottomNavigationBar()
}



//<----------BasicLayoutAppPortrait composable-------------
@Composable
fun BasicLayoutAppPortrait() {
    BasicComposeLayoutsTheme {
        Scaffold(
            bottomBar = { BasicLayoutBottomNavigationBar() }
        ) {
            paddingValues -> HomeScreen(Modifier.padding(paddingValues))
        }
    }
}

@Preview
@Composable
fun BasicLayoutAppPortraitPreview() {
    BasicLayoutAppPortrait()
}



//<--------------BasicLayoutNavigationRail composable---------------
@Composable
fun BasicLayoutNavigationRail( modifier: Modifier = Modifier ) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(R.string.home))
                }
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(R.string.profile))
                }
            )
        }
    }
}

@Preview( name = "BasicLayoutNavigationRailPreview", showBackground = true )
@Composable
fun BasicLayoutNavigationRailPreview() {
    BasicLayoutNavigationRail()
}



//<-------------BasicLayoutLandScape composable-----------------
@Composable
fun BasicLayoutLandScape() {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Row {
            BasicLayoutNavigationRail()
            HomeScreen()
        }
    }
}

@Preview( name = "BasicLayoutLandScapePreview", showBackground = true )
@Composable
fun BasicLayoutLandScapePreview() {
    BasicLayoutLandScape()
}



//<--------------BasicLayoutApp composable---------------
@Composable
fun BasicLayoutApp( windowSize: WindowSizeClass ) {
    when (windowSize.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            BasicLayoutAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            BasicLayoutLandScape()
        }
    }
}
