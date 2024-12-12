package oncall.presentation.util

fun <T> retryWhenNoException(action : () -> T): T{
    while (true){
        try {
            return action()
        }catch (e: IllegalArgumentException){
            println("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.")
        }
    }
}