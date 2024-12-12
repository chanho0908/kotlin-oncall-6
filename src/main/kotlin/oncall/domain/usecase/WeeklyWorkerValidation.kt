package oncall.domain.usecase

class WeeklyWorkerValidation {
    operator fun invoke(input: String): List<String> {
        return validate(input)
    }

    private fun validate(input: String): List<String> {
        isEmpty(input)
        isValidForm(input)
        val spliterator = input.split(",").filter { it.isNotBlank() }
        spliteratorValidate(spliterator)
        return spliterator
    }

    private fun isEmpty(input: String){
        requireNotNull(input.isNotBlank()){ throw IllegalArgumentException() }
    }

    private fun isValidForm(input: String) {
        val regex = Regex("^[가-힣,]+")
        require(regex.matches(input)) { throw IllegalArgumentException() }
    }

    private fun spliteratorValidate(spliterator: List<String>) {
        isInvalidWorkerSize(spliterator)
        isDuplicated(spliterator)
        isValidNameLength(spliterator)
    }

    private fun isInvalidWorkerSize(spliterator: List<String>) {
        val range = 5..35
        require(spliterator.size in range) { throw IllegalArgumentException() }
    }

    private fun isDuplicated(spliterator: List<String>) {
        val map = spliterator.groupingBy { it }.eachCount()
        require(map.all { it.value == 1 }) { throw IllegalArgumentException() }
    }

    private fun isValidNameLength(spliterator: List<String>) {
        require(spliterator.all { it.length <= 5 }) { throw IllegalArgumentException() }
    }
}