package ziox.ramiro.saes.features.presentation.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FactCheck
import androidx.compose.material.icons.rounded.Feed
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ziox.ramiro.saes.features.ui.components.GradesItem
import ziox.ramiro.saes.features.ui.components.RecentActivityItem
import ziox.ramiro.saes.ui.theme.getCurrentTheme
import kotlin.random.Random

@Composable
fun Home() = Column {
    HomeItem(
        modifier = Modifier.padding(start = 32.dp, end = 32.dp),
        title = "Actividad reciente",
        icon = Icons.Rounded.History
    ){
        Row(
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            RecentActivityItem(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            )
            RecentActivityItem(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            )
            RecentActivityItem(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            )
        }
    }
    HomeItem(
        modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp),
        title = "Calificaciones",
        icon = Icons.Rounded.FactCheck
    ){
        LazyRow(
            contentPadding = PaddingValues(horizontal = 28.dp)
        ) {
            items(15){
                GradesItem(
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
    HomeItem(
        modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp),
        title = "Noticias",
        icon = Icons.Rounded.Feed
    ){

    }
}

@Preview
@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    title: String = "Title",
    icon: ImageVector = Icons.Rounded.History,
    content: @Composable () -> Unit = {}
) = Column {
    Row(
        modifier = modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            imageVector = icon,
            contentDescription = "Section icon"
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h5
        )
    }
    content()
}