package app.numbers.domain.repository

import app.numbers.domain.model.NumberFact

interface NumbersRepository {
    suspend fun getNumberFact(number: String): NumberFact
}