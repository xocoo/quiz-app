package khosbayar.miu.quizapp.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val ans1: String,
    val ans2: String,
    val ans3: String,
    val ans4: String,
    val correct_ans: String
)