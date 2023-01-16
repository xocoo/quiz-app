package khosbayar.miu.quizapp.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private var score = 0
    private var scoreLiveData = MutableLiveData<Int>()
    fun getInitialScore(): MutableLiveData<Int> {
        scoreLiveData.value = score
        return scoreLiveData
    }

    fun getCurrentScore() {
        score += 1
        scoreLiveData.value = score
    }

    fun getFinalScore(): MutableLiveData<Int> {
        return scoreLiveData
    }

    fun reset() {
        score = 0
        scoreLiveData.value = 0
    }
}
