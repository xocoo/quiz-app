package khosbayar.miu.quizapp.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import khosbayar.miu.quizapp.DB.Quiz
import khosbayar.miu.quizapp.DB.QuizDatabase
import khosbayar.miu.quizapp.R
import khosbayar.miu.quizapp.Utils.BaseFragment
import kotlinx.coroutines.launch

class ResultFragment : BaseFragment() {
    private lateinit var questions: List<Quiz>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        val listView = view.findViewById<ListView>(R.id.list_view)
        val answers = ResultFragmentArgs.fromBundle(requireArguments()).answers
        Log.d("Result Answers", "${answers.toList()}")
        launch {
            context?.let {
                questions = QuizDatabase(it).getQuizDao().getAllQuizes()
                questions.forEach { q ->
                    q.correct_ans
                }
                listView.adapter = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1,
                    prepResultAnalysis(questions, answers.toList())
                )
            }
        }

        return view
    }

    private fun prepResultAnalysis(questions: List<Quiz>, answers: List<String>): List<String> {
        val items = mutableListOf<String>()
        questions.forEachIndexed { index, quiz ->
            val listItem = String.format(
                "%s. %s\nYour answer: %s \nCorrect answer: %s",
                quiz.id,
                quiz.question,
                answers[quiz.id],
                quiz.correct_ans
            )
            items.add(listItem)

        }

        return items
    }

}