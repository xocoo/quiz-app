package khosbayar.miu.quizapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import khosbayar.miu.quizapp.DB.Quiz
import khosbayar.miu.quizapp.DB.QuizDatabase
import khosbayar.miu.quizapp.R
import khosbayar.miu.quizapp.Utils.BaseFragment
import khosbayar.miu.quizapp.Utils.toast
import kotlinx.coroutines.launch


class QuizFragment : BaseFragment() {

    private lateinit var tvQuestion: TextView
    private lateinit var tvQuestionId: TextView
    private lateinit var tvScore: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var questions: List<Quiz>
    private var qstnIdx = 0
    private var quizViewModel: QuizViewModel? = null
    private var selectedChoice: String? = null
    private var answers: MutableList<String> = mutableListOf()
    private lateinit var currentQuiz: Quiz
    var selectedRadioButton: RadioButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        val skipBtn = view.findViewById<Button>(R.id.btn_qstn_skip)
        val homeBtn = view.findViewById<Button>(R.id.btn_home)
        val nextBtn = view.findViewById<Button>(R.id.btn_qstn_next)
        tvQuestion = view.findViewById(R.id.tv_question)
        tvQuestionId = view.findViewById(R.id.tv_question_id)
        tvScore = view.findViewById(R.id.tv_score)
        quizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        launch {
            context?.let {
                questions = QuizDatabase(it).getQuizDao().getAllQuizes()
                nextQuestion(view)
            }
        }
        homeBtn.setOnClickListener {
            quizViewModel!!.reset()
            Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_splashFragment)
        }

        skipBtn.setOnClickListener {
            nextQuestion(view)
        }
        nextBtn.setOnClickListener {
            if (selectedChoice != null) {
                checkAnswer(selectedChoice!!)
                nextQuestion(view)
            } else context?.toast(getString(R.string.answer_toast_message))

        }
        radioGroup = view.findViewById(R.id.question_radio)

        radioGroup.setOnCheckedChangeListener(this::getAnwser)
        return view
    }

    private fun nextQuestion(view: View) {
        val totalQuestions: Int = questions.size
        val scoreLiveData: MutableLiveData<Int> = quizViewModel!!.getInitialScore()
        scoreLiveData.observe(viewLifecycleOwner) {
            tvScore.text = String.format("%d/%d", it, totalQuestions)
        }
        val selectedAns = if (selectedChoice != null) selectedChoice else ""
        answers.add(selectedAns!!)
        if (qstnIdx == totalQuestions) {
            val action = QuizFragmentDirections.actionQuizFragmentToScoreFragment(
                score = quizViewModel?.getFinalScore()?.value!!,
                answers = answers.toTypedArray(),
                totalQuestions = totalQuestions
            )
            Navigation.findNavController(requireView()).navigate(action)
            return
        }
        currentQuiz = questions[qstnIdx]
        tvQuestion.text = currentQuiz.question
        tvQuestionId.text = currentQuiz.id.toString()
        val radioGroup = view.findViewById(R.id.question_radio) as RadioGroup
        val questionChoices =
            listOf(currentQuiz.ans1, currentQuiz.ans2, currentQuiz.ans3, currentQuiz.ans4)
        for (i in 0 until radioGroup.childCount) {
            (radioGroup.getChildAt(i) as RadioButton).text = questionChoices[i]
        }
        qstnIdx++
        selectedChoice = null
        radioGroup.clearCheck()
    }

    private fun getAnwser(group: RadioGroup, checkedId: Int) {
        val radio = view?.findViewById<RadioButton>(checkedId)
        when (checkedId) {
            R.id.radio_q1_a -> if (radio != null) {
                selectedChoice = radio.text as String?
            }
            R.id.radio_q1_b -> if (radio != null) {
                selectedChoice = radio.text as String?
            }
            R.id.radio_q1_c -> if (radio != null) {
                selectedChoice = radio.text as String?
            }
            R.id.radio_q1_d -> if (radio != null) {
                selectedChoice = radio.text as String?
            }
        }
    }

    private fun checkAnswer(ans: String) {
        if (currentQuiz.correct_ans == ans) {
            quizViewModel!!.getCurrentScore()
        }
    }

}