package com.example.quizapp

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val  CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,"What country does this flag belong to?",
             R.drawable.qes1,
            "Uganda","Burundi","Tanzania","Ecuador",
            4
        )
        questionsList.add(que1)

        // 2

        val que2 = Question(
            1,"What country does this flag belong to?",
            R.drawable.qes2,
            "Chile","Brazil","Argentina","Ghana",
            3
        )
        questionsList.add(que2)

        //3
        val que3 = Question(
            1,"What country does this flag belong to?",
            R.drawable.qes3,
            "Belgium","Germany","Hungary","Romania",
            1
        )
        questionsList.add(que3)


        return questionsList
    }
}