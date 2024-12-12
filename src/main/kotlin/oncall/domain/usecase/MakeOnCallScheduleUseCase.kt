package oncall.domain.usecase

import oncall.domain.model.Daily
import oncall.domain.model.DailyType

class MakeOnCallScheduleUseCase {
    operator fun invoke(
        calendar: List<Daily>,
        weeklySchedule: List<String>,
        holidaySchedule: List<String>
    ): List<String> {
        val base = makeBaseSchedule(calendar, weeklySchedule, holidaySchedule)
        val result = changeSequence(base)
        return result
    }

    private fun makeBaseSchedule(
        calendar: List<Daily>,
        weeklySchedule: List<String>,
        holidaySchedule: List<String>
    ): MutableList<String> {
        var weeklyScheduleIndex = 0
        var holidayScheduleIndex = 0
        val onCall = mutableListOf<String>()

        calendar.forEach { daily ->
            if (weeklyScheduleIndex == weeklySchedule.size) weeklyScheduleIndex = 0
            if (holidayScheduleIndex == holidaySchedule.size) holidayScheduleIndex = 0
            when(daily.isHoliday){
                DailyType.WEEKDAYS -> {
                    onCall.add(weeklySchedule[weeklyScheduleIndex])
                    weeklyScheduleIndex++
                }
                DailyType.WEEKEND,
                DailyType.HOLIDAY -> {
                    onCall.add(holidaySchedule[holidayScheduleIndex])
                    holidayScheduleIndex++
                }
            }
        }
        return onCall
    }

    private fun changeSequence(onCall: List<String>): List<String> {
        val mutableOnCall = onCall.toMutableList()
        mutableOnCall.forEachIndexed { index, worker ->
            if (index == onCall.size - 1) return@forEachIndexed
            val nextWorker = mutableOnCall[index + 1]
            if (worker == nextWorker) {
                mutableOnCall[index + 1] = mutableOnCall[index + 2]
                mutableOnCall[index + 2] = worker
            }
        }
        return mutableOnCall.toList()
    }
}