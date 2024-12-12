package oncall.presentation.vm

import oncall.domain.usecase.HolidayWorkerValidation
import oncall.domain.usecase.MakeCalendarUseCase
import oncall.domain.usecase.MakeOnCallScheduleUseCase
import oncall.domain.usecase.MonthValidationUseCase
import oncall.domain.usecase.WeeklyWorkerValidation
import oncall.presentation.vm.model.OnCallState

class ViewModel(
    private val monthValidationUseCase: MonthValidationUseCase,
    private val weeklyWorkerValidation: WeeklyWorkerValidation,
    private val holidayWorkerValidation: HolidayWorkerValidation,
    private val makeCalendarUseCase: MakeCalendarUseCase,
    private val makeOnCallScheduleUseCase: MakeOnCallScheduleUseCase
) {
    private var _state = OnCallState.create()
    val state get() = _state

    fun onCompleteInputTargetMonth(input: String){
        val target = monthValidationUseCase(input)
        _state = _state.copy(targetMonth = target)
    }

    fun onCompleteInputWeeklySchedule(input: String){
        val weeklyWorker = weeklyWorkerValidation(input)
        _state = _state.copy(weeklySchedule = weeklyWorker)
    }

    fun onCompleteInputHolidaySchedule(input: String){
        val holidayWorker = holidayWorkerValidation(input, _state.weeklySchedule)
        _state = _state.copy(holidaySchedule = holidayWorker)
    }

    fun makeCalendar(){
        val calendar = makeCalendarUseCase(_state.targetMonth)
        _state = _state.copy(calendar = calendar)
    }

    fun makeOnCallSchedule(){
        val onCallSchedule = makeOnCallScheduleUseCase(
            calendar = _state.calendar,
            weeklySchedule = _state.weeklySchedule,
            holidaySchedule = _state.holidaySchedule
        )
        _state = _state.copy(onCall = onCallSchedule)
    }
}