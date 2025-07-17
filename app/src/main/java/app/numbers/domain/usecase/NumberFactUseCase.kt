package app.numbers.domain.usecase

import app.numbers.domain.model.NumberFact
import app.numbers.domain.repository.NumbersRepository

class NumberFactUseCase(
    private val repository: NumbersRepository
) {
    suspend operator fun invoke(number: String): NumberFact {
        return repository.getNumberFact(number)
    }
}