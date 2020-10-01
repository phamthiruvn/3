package view

import tornadofx.*
import controller.Controller
import kotlin.system.exitProcess

class GameView : View() {
    private val controller = Controller()
    private val board = Board()
    override val root = borderpane {
        top = vbox {
            button("Play Again") {
                setOnAction { controller.newBoard(board.grid) }
            }
            button("Close") {
                setOnAction { exitProcess(0) }
            }
        }
        center = board.root
    }

    init {
        title = "TicTacToe"
        primaryStage.width = 800.0
        primaryStage.height = 860.0
    }
}

