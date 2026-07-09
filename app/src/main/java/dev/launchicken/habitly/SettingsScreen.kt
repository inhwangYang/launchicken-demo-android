package dev.launchicken.habitly

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    var reminder by rememberSaveable { mutableStateOf(true) }
    var weekStartsMonday by rememberSaveable { mutableStateOf(true) }
    var cloudSync by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("설정", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
        ) {
            Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(54.dp).background(Color(0xFF12A5B8).copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("🐥", fontSize = 26.sp)
                }
                Spacer(Modifier.width(14.dp))
                Column {
                    Text("습관 만드는 중", fontWeight = FontWeight.Bold)
                    Text("21일째 함께하고 있어요", fontSize = 12.sp, color = Color(0xFF8A9299))
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
        ) {
            Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp)) {
                SettingRow("매일 리마인더", reminder) { reminder = it }
                HorizontalDivider(color = Color(0xFFF0F2F4))
                SettingRow("월요일부터 시작", weekStartsMonday) { weekStartsMonday = it }
                HorizontalDivider(color = Color(0xFFF0F2F4))
                SettingRow("클라우드 동기화", cloudSync) { cloudSync = it }
            }
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
        ) {
            Row(Modifier.fillMaxWidth().padding(16.dp)) {
                Text("버전", modifier = Modifier.weight(1f), fontWeight = FontWeight.Medium)
                Text("1.0.0", color = Color(0xFF8A9299))
            }
        }
    }
}

@Composable
private fun SettingRow(title: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        Modifier.fillMaxWidth().height(52.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onChange)
    }
}
