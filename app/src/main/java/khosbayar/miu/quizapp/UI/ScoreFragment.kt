package khosbayar.miu.quizapp.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import khosbayar.miu.quizapp.R
import khosbayar.miu.quizapp.Utils.BaseFragment

class ScoreFragment : BaseFragment() {
    private lateinit var tvScore: TextView
    private lateinit var btnResult: Button
    private lateinit var btnTryAgain: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_score, container, false)
        tvScore = view.findViewById(R.id.tv_score)
        val score = ScoreFragmentArgs.fromBundle(requireArguments()).score
        val answers = ScoreFragmentArgs.fromBundle(requireArguments()).answers
        val totalQuestions = ScoreFragmentArgs.fromBundle(requireArguments()).totalQuestions
        val wrongAnswers = totalQuestions - score
        val finalScore = "$score/$totalQuestions"
        val scoreResult = String.format(
            "Total Questions: %d\n\nCorrect Answers(Score): %d\n\nWrong Answer: %d\n\nYour Score is: %s",
            totalQuestions, score, wrongAnswers, finalScore
        )
        tvScore.text = scoreResult
        btnResult = view.findViewById(R.id.btn_result_analysis)
        btnTryAgain = view.findViewById(R.id.btn_try_again)
        btnTryAgain.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_scoreFragment_to_quizFragment)
        }
        btnResult.setOnClickListener {
            val action = ScoreFragmentDirections.actionScoreFragmentToResultFragment(answers)
            Navigation.findNavController(requireView()).navigate(action)
        }
        return view
    }

}