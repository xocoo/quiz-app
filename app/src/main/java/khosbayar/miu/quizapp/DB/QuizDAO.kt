package khosbayar.miu.quizapp.DB

import androidx.room.*

@Dao

interface QuizDAO {
    @Query("SELECT * FROM QUIZ")
    suspend fun getAllQuizes(): List<Quiz>

    @Insert
    suspend fun addQuiz(quiz: Quiz)

    @Insert
    suspend fun addMultipleQuizes(vararg quiz: Quiz)

    @Update
    suspend fun updateQuiz(quiz: Quiz)

    @Delete
    suspend fun deleteQuiz(quiz: Quiz)

    @Query("DELETE FROM QUIZ WHERE 1=1")
    suspend fun deleteAllQuiz()
}