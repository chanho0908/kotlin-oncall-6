package oncall.domain.resource

enum class Output(private val value: String) {
    INPUT_MONTH("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
    INPUT_WEEK("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>"),
    INPUT_HOLIDAY("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
    WEEKEND_RESULT("%d월 %d일 %s %s"),
    HOLIDAY_RESULT("%d월 %d일 %s(휴일) %s");

    override fun toString(): String = value

    companion object{
        fun weekDaysFormat(month: Int, day: Int, weekDays: String, name: String): String {
            return WEEKEND_RESULT.value.format(month, day, weekDays, name)
        }

        fun holidayFormat(month: Int, day: Int, weekDays: String, name: String): String {
            return HOLIDAY_RESULT.value.format(month, day, weekDays, name)
        }
    }
}