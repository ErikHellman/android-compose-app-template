package se.hellsoft.composetemplate.ui.screens

import android.Manifest
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
        )

        when {
            cameraPermissionState.status.isGranted -> {
                Text(
                    text = "Camera permission granted",
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
            cameraPermissionState.status.shouldShowRationale -> {
                Text(
                    text = "Camera permission is needed for this feature",
                    modifier = Modifier.padding(top = 16.dp),
                )
                Button(
                    onClick = { cameraPermissionState.launchPermissionRequest() },
                    modifier = Modifier.padding(top = 8.dp),
                ) {
                    Text("Grant Camera Permission")
                }
            }
            else -> {
                Button(
                    onClick = { cameraPermissionState.launchPermissionRequest() },
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    Text("Request Camera Permission")
                }
            }
        }

        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Text("Back")
        }
    }
}
