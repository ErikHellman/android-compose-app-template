package se.hellsoft.composetemplate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToDetail: () -> Unit,
    onNavigateToSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.headlineMedium,
        )
        Button(
            onClick = onNavigateToDetail,
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Text("View Detail")
        }
        Button(
            onClick = onNavigateToSettings,
            modifier = Modifier.padding(top = 8.dp),
        ) {
            Text("Settings")
        }
    }
}
