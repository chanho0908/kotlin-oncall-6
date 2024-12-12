package oncall.domain.usecase

import oncall.domain.model.Daily
import oncall.domain.model.DailyType
import oncall.domain.model.Holiday
import oncall.domain.model.TargetMonth
import oncall.domain.model.Week

class MakeCalendarUseCase {
    operator fun invoke(target: TargetMonth): List<Daily> {
        val holiday = Holiday.from(target.month)
        val rage = 1..target.month.endDay
        val calendar = setSchedule(rage,holiday, target)
        return calendar.toList()
    }

    private fun setSchedule(rage: IntRange, holiday: Int, target: TargetMonth): List<Daily> {
        var weekdaysIndex = Week.entries.indexOf(target.weekOfDays)
        val calendar = mutableListOf<Daily>()
        rage.forEach { idx ->
            if (weekdaysIndex == Week.entries.size) weekdaysIndex = 0
            val weekdays = Week.entries[weekdaysIndex]
            val isHoliday = determineDailyType(holiday, weekdaysIndex, idx)
            calendar.add(Daily(idx, weekdays, isHoliday))
            weekdaysIndex++
        }
        return calendar.toList()
    }

    private fun determineDailyType(holiday: Int, weekdaysIndex: Int, idx: Int): DailyType {
        if (holiday == idx) return DailyType.HOLIDAY
        if (weekdaysIndex > 4) return DailyType.WEEKEND
        return DailyType.WEEKDAYS
    }
}