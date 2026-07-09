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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onAddHabit: () -> Unit = {}) {
    Box(modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text("오늘의 습관", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(12.dp))
                SummaryCard()
                Spacer(Modifier.height(6.dp))
            }
            items(SampleData.habits) { habit -> HabitRow(habit) }
        }
        FloatingActionButton(
            onClick = onAddHabit,
            modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp),
        ) {
            Icon(Icons.Filled.Add, contentDescription = "새 습관")
        }
    }
}

@Composable
private fun SummaryCard() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(listOf(Color(0xFF19B8CC), Color(0xFF2F80ED))),
                RoundedCornerShape(24.dp),
            )
            .padding(20.dp)
    ) {
        Text("7월 9일 목요일", color = Color.White.copy(alpha = 0.9f), fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                "${SampleData.completedToday}",
                color = Color.White, fontSize = 44.sp, fontWeight = FontWeight.Black,
            )
            Spacer(Modifier.width(6.dp))
            Text(
                "/ ${SampleData.habits.size}개 완료",
                color = Color.White.copy(alpha = 0.9f), fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
        Spacer(Modifier.height(10.dp))
        LinearProgressIndicator(
            progress = { SampleData.completionRate },
            modifier = Modifier.fillMaxWidth().height(8.dp),
            color = Color.White,
            trackColor = Color.White.copy(alpha = 0.3f),
        )
    }
}

@Composable
private fun HabitRow(habit: Habit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(18.dp),
    ) {
        Row(
            Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier
                    .size(48.dp)
                    .background(habit.color.copy(alpha = 0.15f), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Text(habit.emoji, fontSize = 22.sp)
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(habit.name, fontWeight = FontWeight.SemiBold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.LocalFireDepartment, contentDescription = null,
                        tint = habit.color, modifier = Modifier.size(14.dp),
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        "${habit.streak}일 연속",
                        color = habit.color, fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                    )
                }
            }
            Icon(
                if (habit.doneToday) Icons.Filled.CheckCircle else Icons.Outlined.Circle,
                contentDescription = null,
                tint = if (habit.doneToday) habit.color else Color(0xFFD5D9DD),
                modifier = Modifier.size(30.dp),
            )
        }
    }
}
