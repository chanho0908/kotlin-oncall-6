package oncall.domain.model

data class Daily (
    val day: Int,
    val weekdays: Week,
    val isHoliday: DailyType
)