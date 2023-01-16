package khosbayar.miu.quizapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import khosbayar.miu.quizapp.DB.Quiz
import khosbayar.miu.quizapp.DB.QuizDatabase
import khosbayar.miu.quizapp.R
import khosbayar.miu.quizapp.Utils.BaseFragment
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    private var btn_startquiz: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        btn_startquiz = view.findViewById(R.id.btn_startquiz) as Button
        btn_startquiz!!.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_quizFragment)
            addQuestionsToDB()
        }
        return view
    }

    fun addQuestionsToDB() {
        val quiz1 = Quiz(
            1,
            "Which of the following is the topmost layer of android architecture?",
            "Applications",
            "Applications Framework",
            "System Libraries and Android Runtime",
            "Linux Kernel",
            "Applications"
        )
        val quiz2 = Quiz(
            2,
            "Which of the  following is contained in the src folder?",
            "Java source code",
            "XML",
            "Manifest",
            "None of the above",
            "Java source code"
        )
        val quiz3 = Quiz(
            3,
            "Which of the following method is used to handle what happens after clicking a button?",
            "onSelect",
            "onClick",
            "onCreate",
            "None of the above",
            "onClick"
        )
        val quiz4 = Quiz(
            4,
            "Which of the following android component displays the part of an activity on screen?",
            "Intent",
            "View",
            "Manifest",
            "Fragment",
            "Fragment"
        )
        val quiz5 = Quiz(
            5,
            "Which of the following is the parent class of service?",
            "object",
            "contextThemeWrapper",
            "contextWrapper",
            "context",
            "contextWrapper"
        )
        val quiz6 = Quiz(
            6,
            "Which of the following is the parent class of Activity?",
            "object",
            "contextThemeWrapper",
            "context",
            "None of the above",
            "contextThemeWrapper"
        )
        val quiz7 = Quiz(
            7,
            "In which of the following tab an error is shown?",
            "Memory",
            "ADB Logs",
            "Logcat",
            "CPU",
            "Logcat"
        )
        val quiz8 = Quiz(
            8,
            "Which of the layer is below the topmost layer of android architecture?",
            "Linux Kernel",
            "System Libraries and Android Runtime",
            "Applications",
            "Applications Framework",
            "Applications Framework"
        )
        val quiz9 = Quiz(
            9,
            " In which state the activity is, if it is not in focus, but still visible on the screen?",
            "Destroyed state",
            "Stopped state",
            "Running state",
            "Paused state",
            "Paused state"
        )
        val quiz10 = Quiz(
            10,
            "Which of the following is a dialog class in android?",
            "ProgressDialog",
            "AlertDialog",
            "DatePicker Dialog",
            "All of the above",
            "All of the above"
        )
        val quiz11 = Quiz(
            11,
            "Which of the following is not an activity lifecycle callback method?",
            "onStart() method",
            "onClick() method",
            "onCreate() method",
            "onBackPressed() method",
            "onBackPressed() method"
        )
        val quiz12 = Quiz(
            12,
            "How can we kill an activity in android?",
            "Using finishActivity(int requestCode)",
            "Using finish() method",
            "All of above",
            "None of above",
            "All of above"
        )
        val quiz13 = Quiz(
            13,
            "How can we stop the services in android?",
            "By using the finish() method",
            "By using system.exit() method",
            "By using the stopSelf() and stopService() method",
            "None of the above",
            "By using the stopSelf() and stopService() method"
        )
        val quiz14 = Quiz(
            14,
            "What is contained in manifest.xml?",
            "Permission that the application requires",
            "Source code",
            "List of strings used in the app",
            "None of the above",
            "Permission that the application requires"
        )
        val quiz15 = Quiz(
            15,
            "Which of the following is not a state in the service lifecycle?",
            "Start",
            "Paused",
            "Destroyed",
            "Running",
            "Paused"
        )

        launch {
            context?.let {
                QuizDatabase(it)
                    .getQuizDao().deleteAllQuiz()
                QuizDatabase(it)
                    .getQuizDao().addMultipleQuizes(
                        quiz1, quiz2, quiz3, quiz4, quiz5, quiz6, quiz7, quiz8,
                        quiz9, quiz10, quiz11, quiz12, quiz13, quiz14, quiz15
                    )
            }
        }
    }
}