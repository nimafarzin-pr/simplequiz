
package com.example.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizapp.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>)

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (Or better yet, don't define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
            Question(text = "اون چیه که همه جا هست ولی نمیتونی ببینیش؟",
                    answers = listOf("هوا", "شیشه", "گوش", "اب")),
            Question(text = "اون کدوم حیونه که از هر طرفش نگا کنی میدرتت؟",
                    answers = listOf("گرگ", "اسب", "شغال", "کفتار")),
            Question(text = "اون چیه که سر نداره ولی کلاه داره یه پا داره ولی کفش نداره",
                    answers = listOf("قارچ", "موتور سوار", "حلزون", "تمساح")),
            Question(text = "اون چیه که چه پر و چه خالی وزنش یکیه؟",
                    answers = listOf("نوار", "پنبه", "هوا", "کیف")),
            Question(text = "اون چیه که هم رادیو هم دریا هردو اونو دارن؟",
                    answers = listOf("موج", "مخاطب", "صدا", "نمک")),
            Question(text = "بالای ان جای حساب پایین آن بازی تاب؟",
                    answers = listOf("ساعت پاندول دار", "شهر بازی", "ماشین حساب", "گردنبند")),
            Question(text = "اون چیه که یه چشم و یه پا داره؟",
                    answers = listOf("سوزن", "مداد", "موتورسیکلت", "گردنبند")),
            Question(text = "اون چیه ریشه داره درخت نیست سفیده ولی قند نیست؟",
                    answers = listOf("دندان", "شکر", "ساکاروز", "گل")),
            Question(text = "اون چیه مثل سه مغازه تودرتو اولی چرم فروشی دومی پرده فروشی سومی یاقوت فروشی؟",
                    answers = listOf("انار", "اتوبوس", "پاساژ", "گردو")),
            Question(text = "ان کدام گیاه است که اگر نصفش کنی نیم اون لنگ و نیم دیگش غصه هست؟",
                    answers = listOf("شلغم", "سیر", "پیاز", "علف"))
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = ((questions.size + 1) / 2).coerceAtMost(3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        //want your code to refresh the UI with the new data
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(numQuestions,questionIndex))
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
                }
            }
        }
        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}
