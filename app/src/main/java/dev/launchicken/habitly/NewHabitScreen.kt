package dev.launchicken.habitly

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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

private val emojis = listOf("✨", "💪", "🥗", "💊", "🚶", "🎸", "🧹", "💰")
private val palette = listOf(
    Color(0xFF12A5B8), Color(0xFF2F80ED), Color(0xFF9B51E0),
    Color(0xFFEB5757), Color(0xFFF2994A), Color(0xFF27AE60),
)

@Composable
fun NewHabitScreen(onClose: () -> Unit = {}) {
    var name by rememberSaveable { mutableStateOf("") }
    var selectedEmoji by rememberSaveable { mutableStateOf("✨") }
    var selectedColor by rememberSaveable { mutableStateOf(0) }
    var remindMe by rememberSaveable { mutableStateOf(true) }

    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = "닫기")
            }
            Text("새 습관 만들기", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("습관 이름") },
            placeholder = { Text("예: 아침에 이불 정리하기") },
            modifier = Modifier.fillMaxWidth(),
        )
        Column {
            Text("아이콘", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                emojis.forEach { emoji ->
                    Box(
                        Modifier
                            .size(40.dp)
                            .background(
                                if (emoji == selectedEmoji) palette[selectedColor].copy(alpha = 0.2f) else Color.Transparent,
                                RoundedCornerShape(10.dp),
                            )
                            .clickable { selectedEmoji = emoji },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(emoji, fontSize = 20.sp)
                    }
                }
            }
        }
        Column {
            Text("색상", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                palette.forEachIndexed { index, color ->
                    Box(
                        Modifier
                            .size(34.dp)
                            .background(color, CircleShape)
                            .clickable { selectedColor = index },
                        contentAlignment = Alignment.Center,
                    ) {
                        if (index == selectedColor) {
                            Icon(
                                Icons.Filled.Check, contentDescription = null,
                                tint = Color.White, modifier = Modifier.size(16.dp),
                            )
                        }
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("매일 알림 받기", fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            Switch(checked = remindMe, onCheckedChange = { remindMe = it })
        }
        Spacer(Modifier.weight(1f))
        Button(
            onClick = onClose,
            enabled = name.isNotBlank(),
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text("추가하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}
