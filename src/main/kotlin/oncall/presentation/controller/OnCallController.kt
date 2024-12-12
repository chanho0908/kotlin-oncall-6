package oncall.presentation.controller

import oncall.domain.resource.Output
import oncall.presentation.util.retryWhenNoException
import oncall.presentation.view.InputView
import oncall.presentation.view.OutputView
import oncall.presentation.vm.ViewModel

class OnCallController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val viewModel: ViewModel
) {
    fun run(){
        getInput()
        viewModel.makeCalendar()
        viewModel.makeOnCallSchedule()
    }

    private fun getInput(){
        retryWhenNoException {
            inputMonth()
            inputWeeklyWorkerSchedule()
            inputHolidaySchedule()
            outputView.printMessage(viewModel.state.resultMessage)
        }
    }

    private fun inputMonth(){
        outputView.printMessage(Output.INPUT_MONTH.toString())
        val input = inputView.readItem()
        viewModel.onCompleteInputTargetMonth(input)
    }

    private fun inputWeeklyWorkerSchedule(){
        outputView.printMessage(Output.INPUT_WEEK.toString())
        val input = inputView.readItem()
        viewModel.onCompleteInputWeeklySchedule(input)
    }

    private fun inputHolidaySchedule(){
        outputView.printMessage(Output.INPUT_HOLIDAY.toString())
        val input = inputView.readItem()
        viewModel.onCompleteInputHolidaySchedule(input)
    }
}