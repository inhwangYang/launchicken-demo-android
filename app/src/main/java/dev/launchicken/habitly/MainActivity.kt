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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF12A5B8),
                    surface = Color(0xFFF7F9FA),
                    background = Color(0xFFF2F4F5),
                )
            ) {
                HabitlyApp()
            }
        }
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
