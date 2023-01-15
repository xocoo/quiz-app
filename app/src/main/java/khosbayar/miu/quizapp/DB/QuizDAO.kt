package khosbayar.miu.quizapp.DB

import androidx.room.*

@Dao

interface QuizDAO {
    @Query("SELECT * FROM Quiz ORDER BY id")
    fun getAllQuizes(): List<Quiz>

    @Insert
    fun addQuiz(quiz: Quiz)

    @Insert
    fun addMultipleQuizes(vararg quiz: Quiz)

    @Update
    fun updateQuiz(quiz: Quiz)

    @Delete
    fun deleteQuiz(quiz: Quiz)
}