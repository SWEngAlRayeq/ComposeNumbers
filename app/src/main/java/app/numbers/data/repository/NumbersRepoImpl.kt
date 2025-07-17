package app.numbers.data.repository

import app.numbers.data.remote.NumbersApi
import app.numbers.domain.model.NumberFact
import app.numbers.domain.repository.NumbersRepository
import javax.inject.Inject

class NumbersRepoImpl @Inject constructor (
    private val api: NumbersApi
) : NumbersRepository {
    override suspend fun getNumberFact(number: String): NumberFact {
        val fact = api.getNumberFact(number)
        return NumberFact(number, fact)
    }
}