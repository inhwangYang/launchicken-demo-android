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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val weekdays = listOf("월", "화", "수", "목", "금", "토", "일")

@Composable
fun StatsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("통계", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard("오늘 달성률", "${(SampleData.completionRate * 100).toInt()}%", Color(0xFF12A5B8), Modifier.weight(1f))
            StatCard("최고 연속", "${SampleData.bestStreak}일", Color(0xFFF2994A), Modifier.weight(1f))
        }
        WeeklyChartCard()
        BreakdownCard()
    }
}

@Composable
private fun StatCard(title: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, color = color, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(6.dp))
            Text(value, fontSize = 30.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
private fun WeeklyChartCard() {
    val counts = (0 until 7).map { day -> SampleData.habits.count { it.week[day] } }
    val max = SampleData.habits.size.coerceAtLeast(1)
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(Modifier.fillMaxWidth().padding(18.dp)) {
            Text("최근 7일", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(14.dp))
            Row(
                Modifier.fillMaxWidth().height(140.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                counts.forEachIndexed { index, count ->
                    Column(
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height((count.toFloat() / max * 110).coerceAtLeast(8f).dp)
                                .background(
                                    if (index == 6) Color(0xFF12A5B8) else Color(0xFF12A5B8).copy(alpha = 0.35f),
                                    RoundedCornerShape(50),
                                )
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(weekdays[index], fontSize = 11.sp, color = Color(0xFF8A9299))
                    }
                }
            }
        }
    }
}

@Composable
private fun BreakdownCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("습관별 달성", fontWeight = FontWeight.Bold)
            SampleData.habits.forEach { habit ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(habit.emoji)
                    Spacer(Modifier.width(8.dp))
                    Text(habit.name, fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        habit.week.forEach { done ->
                            Box(
                                Modifier
                                    .size(10.dp)
                                    .background(
                                        if (done) habit.color else Color(0xFFE4E7EA),
                                        CircleShape,
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}
