package oncall.domain.model

enum class Week(val value: String) {
    MonDay("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    companion object{
        fun from(value: String): Week {
            return entries.find { it.value == value } ?: throw IllegalArgumentException()
        }
    }
}