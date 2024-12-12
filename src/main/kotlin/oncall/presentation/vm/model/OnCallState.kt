package oncall.presentation.vm.model

import oncall.domain.model.Daily
import oncall.domain.model.DailyType
import oncall.domain.model.Month
import oncall.domain.model.TargetMonth
import oncall.domain.model.Week
import oncall.domain.resource.Output.Companion.holidayFormat
import oncall.domain.resource.Output.Companion.weekDaysFormat

data class OnCallState (
    val targetMonth: TargetMonth,
    val weeklySchedule : List<String>,
    val holidaySchedule : List<String>,
    val calendar: List<Daily>,
    val onCall: List<String>
){
    val resultMessage
        get() = calendar.mapIndexed { idx, daily ->
            when(daily.isHoliday){
                DailyType.WEEKEND,
                DailyType.WEEKDAYS -> {
                    weekDaysFormat(
                        month = targetMonth.month.value,
                        day = idx + 1,
                        weekDays = daily.weekdays.value,
                        name = onCall[idx]
                    )
                }
                DailyType.HOLIDAY -> {
                    holidayFormat(
                        month = targetMonth.month.value,
                        day = idx + 1,
                        weekDays = daily.weekdays.value,
                        name = onCall[idx]
                    )
                }
            }
        }.joinToString("\n")

    companion object {
        fun create(): OnCallState {
            return OnCallState(
                TargetMonth(
                    Month.JULY,
                    Week.MonDay
                ),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList()
            )
        }
    }
}