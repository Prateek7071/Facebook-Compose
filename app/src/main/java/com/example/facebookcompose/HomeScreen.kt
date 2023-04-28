package com.example.facebookcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Store
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.material.icons.rounded.VideoCall
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.facebookcompose.ui.theme.BrandBlue
import com.example.facebookcompose.ui.theme.ButtonGray

@Composable
fun HomeScreen() {
    Box(
        modifier =
        Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        LazyColumn(
        ) {
            item {
                TopBarApp()
            }
            item {
                TabBar()
            }
            item { 
                StatusUpdateBar(avatarUrl = "https://avatars.githubusercontent.com/u/106758759?v=4",
                                onTextchange = {},
                                onSendClick={})
            }
        }
    }
}

@Composable
private fun TopBarApp() {
    Surface() {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = BrandBlue
            )
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(CircleShape)
                    .background(ButtonGray)

            ) {
                Icon(Icons.Rounded.Search, contentDescription = stringResource(R.string.search))

            }
            Spacer(Modifier.width(8.dp))
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(CircleShape)
                    .background(ButtonGray)
            ) {
                Icon(Icons.Rounded.ChatBubble, contentDescription = stringResource(R.string.chat))
            }


        }
    }
}

data class TabItems(
    val icon: ImageVector,
    val contentDescriptor: String
)

@Composable
private fun TabBar() {
    Surface() {
        var tabindex by remember {
            mutableStateOf(0)
        }
        TabRow(
            selectedTabIndex = tabindex,
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary

            ) {

            val tabs = listOf(
                TabItems(Icons.Rounded.Home, stringResource(R.string.home)),
                TabItems(Icons.Rounded.Tv,stringResource(R.string.reels)),
                TabItems(Icons.Rounded.Store,stringResource(R.string.marketplace)),
                TabItems(Icons.Rounded.Newspaper,stringResource(R.string.newspaper)),
                TabItems(Icons.Rounded.Notifications,stringResource(R.string.notification)),
                TabItems(Icons.Rounded.Menu,stringResource(R.string.menu)),
            )

            tabs.forEachIndexed() { i, item ->
                Tab(
                    selected = tabindex == i,
                    onClick = { tabindex = i },
                    modifier = Modifier.heightIn(48.dp)
                ) {
                    Icon(item.icon, contentDescription = item.contentDescriptor,
                        tint = if (i == tabindex) {
                            MaterialTheme.colors.primary
                        } else {
                            MaterialTheme.colors.onSurface.copy(alpha = 0.44f)
                        }
                    )
                }
            }


        }
    }
}

@Composable
private fun StatusUpdateBar(avatarUrl:String,
                            onTextchange:(String)->Unit,
                            onSendClick:()->Unit){
    Surface() {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 12.dp
                    ), verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(avatarUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_placeholder)
                        .build(), contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )

                var text by remember {
                    mutableStateOf("")
                }
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),

                    modifier = Modifier.fillMaxWidth(),

                    value = text,
                    onValueChange = {
                        text = it
                        onTextchange(it)
                    },
                    placeholder = {
                        Text(stringResource(R.string.what_s_on_your_mind))
                    },

                    keyboardActions = KeyboardActions(
                        onSend = {
                            onSendClick()
                            text = ""
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send,
                    )
                )
            }

            Divider()

            Row{
                StatusAction(
                    Icons.Rounded.VideoCall,
                    stringResource(R.string.live),
                    modifier = Modifier.weight(1f)
                )
                StatusAction(
                    Icons.Rounded.VideoCall,
                    stringResource(R.string.live),
                    modifier=Modifier.weight(1f)
                )
                StatusAction(
                    Icons.Rounded.VideoCall,
                    stringResource(R.string.live),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun StatusAction(
    icon: ImageVector,
    text: String,
    modifier: Modifier=Modifier
) {
    Row(Modifier.fillMaxWidth( )) {
        TextButton(
            modifier=modifier,
            onClick = { },
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.onSurface
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon,
                    contentDescription = text)
                Spacer(Modifier.width(8.dp))
                Text(text)
            }
        }
    }
}
