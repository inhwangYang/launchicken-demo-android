package dev.launchicken.habitly

import androidx.compose.ui.graphics.Color

data class Habit(
    val emoji: String,
    val name: String,
    val color: Color,
    val streak: Int,
    val doneToday: Boolean,
    /** 최근 7일 달성 여부 (오래된 날 -> 오늘) */
    val week: List<Boolean>,
)

object SampleData {
    val habits = listOf(
        Habit("💧", "물 2L 마시기", Color(0xFF2F80ED), 12, true,
            listOf(true, true, false, true, true, true, true)),
        Habit("🧘", "아침 스트레칭 10분", Color(0xFFF2994A), 5, true,
            listOf(false, true, true, true, false, true, true)),
        Habit("📚", "책 20쪽 읽기", Color(0xFF9B51E0), 21, false,
            listOf(true, true, true, true, true, true, false)),
        Habit("🏃", "5km 러닝", Color(0xFF27AE60), 3, true,
            listOf(false, false, true, false, true, true, true)),
        Habit("🌙", "12시 전에 잠들기", Color(0xFF5E60CE), 8, false,
            listOf(true, true, true, false, true, true, false)),
    )

    val completedToday = habits.count { it.doneToday }
    val completionRate = if (habits.isEmpty()) 0f else completedToday.toFloat() / habits.size
    val bestStreak = habits.maxOf { it.streak }
}
