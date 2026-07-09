package dev.launchicken.habitly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Name of the intent extra Launchicken uses to request a specific screen for screenshots.
 * When present, the app must render that screen directly with seeded demo data and without
 * requiring any user input, auth, or network/services access.
 */
const val LAUNCHICKEN_SCREEN_EXTRA = "launchicken_screen"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Screenshot mode: the extra is only ever set by Launchicken's CI instrumentation.
        // Normal app launches never carry this extra, so behavior below is unchanged for users.
        val screenshotScreen = intent?.getStringExtra(LAUNCHICKEN_SCREEN_EXTRA)

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF12A5B8),
                    surface = Color(0xFFF7F9FA),
                    background = Color(0xFFF2F4F5),
                )
            ) {
                if (screenshotScreen != null) {
                    LaunchickenScreenshotHost(screenshotScreen)
                } else {
                    HabitlyApp()
                }
            }
        }
    }
}

/**
 * Renders a single screen directly (bypassing tabs/navigation/dialogs) for screenshot capture.
 * Falls back to the home screen for unknown screen names so we never show a blank screen.
 */
@Composable
fun LaunchickenScreenshotHost(screen: String) {
    when (screen) {
        "home" -> Scaffold { padding -> HomeScreen(Modifier.padding(padding)) }
        "stats" -> Scaffold { padding -> StatsScreen(Modifier.padding(padding)) }
        "new-habit" -> NewHabitScreen(onClose = {})
        "settings" -> Scaffold { padding -> SettingsScreen(Modifier.padding(padding)) }
        else -> Scaffold { padding -> HomeScreen(Modifier.padding(padding)) }
    }
}

@Composable
fun HabitlyApp() {
    var tab by rememberSaveable { mutableStateOf(0) }
    var showNewHabit by rememberSaveable { mutableStateOf(false) }

    if (showNewHabit) {
        NewHabitScreen(onClose = { showNewHabit = false })
        return
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = tab == 0, onClick = { tab = 0 },
                    icon = { Icon(Icons.Filled.CheckCircle, contentDescription = null) },
                    label = { Text("오늘") },
                )
                NavigationBarItem(
                    selected = tab == 1, onClick = { tab = 1 },
                    icon = { Icon(Icons.Filled.BarChart, contentDescription = null) },
                    label = { Text("통계") },
                )
                NavigationBarItem(
                    selected = tab == 2, onClick = { tab = 2 },
                    icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
                    label = { Text("설정") },
                )
            }
        }
    ) { padding ->
        val modifier = Modifier.padding(padding)
        when (tab) {
            0 -> HomeScreen(modifier, onAddHabit = { showNewHabit = true })
            1 -> StatsScreen(modifier)
            else -> SettingsScreen(modifier)
        }
    }
}
