package oncall.domain.usecase

import oncall.domain.model.Month
import oncall.domain.model.Week
import oncall.domain.model.TargetMonth

class MonthValidationUseCase {
    operator fun invoke(input: String): TargetMonth {
        return validate(input)
    }

    private fun validate(input: String): TargetMonth {
        isEmpty(input)
        val spliterator = input.split(",")
        isValidSize(spliterator)
        val month = isNumeric(spliterator[0])
        isValidMonth(month)
        isValidWeek(spliterator[1])

        return TargetMonth(
            Month.from(month),
            Week.from(spliterator[1])
        )
    }

    private fun isEmpty(input: String){
        requireNotNull(input.isNotBlank()){ throw IllegalArgumentException() }
    }

    private fun isValidSize(spliterator: List<String>){
        require(spliterator.size == 2){ throw IllegalArgumentException() }
    }

    private fun isNumeric(input: String): Int{
        val month = input.toIntOrNull() ?: throw IllegalArgumentException()
        return month
    }

    private fun isValidMonth(month: Int){
        require(month in 1..12){ throw IllegalArgumentException() }
    }

    private fun isValidWeek(input: String){
        require(
            enumValues<Week>().map { it.value }.contains(input)){
            throw IllegalArgumentException()
        }
    }
}