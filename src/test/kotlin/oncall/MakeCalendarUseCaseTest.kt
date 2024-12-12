package oncall

import oncall.domain.usecase.MakeCalendarUseCase
import org.junit.jupiter.api.BeforeEach

class MakeCalendarUseCaseTest {
    private lateinit var makeCalendarUseCase: MakeCalendarUseCase

    @BeforeEach
    fun setUp(){
        makeCalendarUseCase = MakeCalendarUseCase()
    }


}