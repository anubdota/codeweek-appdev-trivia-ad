package com.example.triviamaniac

data class Triv (
    val category:String,
    val type: String,
    val difficulty: String,
    var question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
        )

data class QuestionResponse(
    val response_code: Int,
    val results: List<Triv>
)

object values {
    var apiResponseQuestionList : QuestionResponse = QuestionResponse(0, emptyList())
    val data = apiResponseQuestionList.results
}