package view
import javafx.application.Application
import tornadofx.App

class Main : App(GameView::class) {
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}