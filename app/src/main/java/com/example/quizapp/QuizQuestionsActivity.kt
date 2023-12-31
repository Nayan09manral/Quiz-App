package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList:ArrayList<Question>?= null
    private var mSelectedOptionPosition : Int = 0
    private var mUserName : String?= null
    private var mCorrectAnswers : Int = 0

    private  var progressBar: ProgressBar?=null
    private var tvProgress: TextView?=null
    private var tvquestion: TextView?=null
    private var tvimage: ImageView?=null

    private var tvoptionone: TextView?=null
    private var tvoptiontwo: TextView?=null
    private var tvoptionthree: TextView?=null
    private var tvoptionfour: TextView?=null
    private var btnSubmit : Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvquestion = findViewById(R.id.tv_question)
        tvimage = findViewById(R.id.iv_image)

        tvoptionone = findViewById(R.id.tv_option_one)
        tvoptiontwo = findViewById(R.id.tv_option_two)
        tvoptionthree = findViewById(R.id.tv_option_three)
        tvoptionfour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        tvoptionone?.setOnClickListener(this)
        tvoptiontwo?.setOnClickListener(this)
        tvoptionthree?.setOnClickListener(this)
        tvoptionfour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

       mQuestionsList = Constants.getQuestions()

        setQuestion()


    }

    private fun setQuestion() {

        defaultOptionsView()

        val question: Question = mQuestionsList!![mCurrentPosition-1]
        tvimage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvquestion?.text = question.question
        tvoptionone?.text = question.optionOne
        tvoptiontwo?.text = question.optionTwo
        tvoptionthree?.text = question.optionThree
        tvoptionfour?.text = question.optionFour

        if(mCurrentPosition == mQuestionsList!!.size){

            btnSubmit?.text = "FiINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvoptionone?.let {
            options.add(0,it)
        }
        tvoptiontwo?.let {
            options.add(1,it)
        }
        tvoptionthree?.let {
            options.add(2,it)
        }
        tvoptionfour?.let {
            options.add(3,it)
        }
        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border
        )
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                tvoptionone?.let {
                    selectedOptionView(it ,1)
                }
        }

            R.id.tv_option_two -> {
                tvoptiontwo?.let {
                    selectedOptionView(it ,2)
                }
            }

            R.id.tv_option_three -> {
                tvoptionthree?.let {
                    selectedOptionView(it ,3)
                }
            }

            R.id.tv_option_four -> {
                tvoptionfour?.let {
                    selectedOptionView(it ,4)
                }
            }

            R.id.btn_submit ->{

                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }
                        else ->{
                            val intent = Intent(this , ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else{
                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer !=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text ="FINISH"
                    }else{
                        btnSubmit?.text ="GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0

                }

            }
    }
    }
    private fun answerView(answer: Int, drawableView : Int){
        when(answer){
            1-> {
                tvoptionone?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

            2-> {
                tvoptiontwo?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

            3-> {
                tvoptionthree?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

            4-> {
                tvoptionfour?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

        }

    }
}