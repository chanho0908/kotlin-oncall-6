package oncall.app

import oncall.domain.usecase.HolidayWorkerValidation
import oncall.domain.usecase.MakeCalendarUseCase
import oncall.domain.usecase.MakeOnCallScheduleUseCase
import oncall.domain.usecase.MonthValidationUseCase
import oncall.domain.usecase.WeeklyWorkerValidation
import oncall.presentation.controller.OnCallController
import oncall.presentation.view.InputView
import oncall.presentation.view.OutputView
import oncall.presentation.vm.ViewModel

class DependencyInjector {
    fun injectViewModel(): ViewModel{
        val monthValidationUseCase = MonthValidationUseCase()
        val weeklyWorkerValidation = WeeklyWorkerValidation()
        val holidayWorkerValidation = HolidayWorkerValidation()
        val makeCalendarUseCase = MakeCalendarUseCase()
        val makeOnCallScheduleUseCase = MakeOnCallScheduleUseCase()
        return ViewModel(
            monthValidationUseCase,
            weeklyWorkerValidation,
            holidayWorkerValidation,
            makeCalendarUseCase,
            makeOnCallScheduleUseCase
        )
    }

    fun injectController(): OnCallController{
        val inputView = InputView()
        val outputView = OutputView()
        val viewModel = injectViewModel()
        return OnCallController(
            inputView,
            outputView,
            viewModel
        )
    }
}