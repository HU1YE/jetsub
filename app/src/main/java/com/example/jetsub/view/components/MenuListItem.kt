package com.example.jetsub.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetsub.model.Menu

@Composable
fun MenuListItem(menu: Menu, onClick: (Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(menu.image)
            }
    ) {
        Image(
            painter = painterResource(id = menu.image),
            contentDescription = null,
            modifier = Modifier.width(100.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = menu.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "On Cart: ${menu.onCart}", style = MaterialTheme.typography.titleSmall)
        }
    }
}