package oncall

import oncall.app.DependencyInjector

fun main() {
    val di = DependencyInjector()
    val onCallController = di.injectController()

    onCallController.run()
}
