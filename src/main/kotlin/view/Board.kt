package view

import controller.Controller
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeType
import model.TicBoard
import tornadofx.*
import kotlin.system.exitProcess

class Board : View() {
    private val tic = TicBoard()
    private val controller = Controller()
    override var root = borderpane {
        top = hbox {
            alignment = Pos.CENTER
            button("New Game") {
                setPrefSize(100.0, 75.0);
                setOnAction { controller.newBoard(tic) }
            }
            button("Exit") {
                setPrefSize(100.0, 75.0);
                setOnAction { exitProcess(0) }
            }
        }
        center = stackpane {
            fitToParentSize()
            alignment = Pos.BOTTOM_CENTER
            tic.grid = gridpane {
                maxHeightProperty().bind(this@stackpane.widthProperty())
                maxWidthProperty().bind(this@stackpane.heightProperty())
                prefHeightProperty().bind(this@stackpane.heightProperty())
                prefWidthProperty().bind(this@stackpane.widthProperty())
                for (row in 0 until tic.boardWidth) {
                    row {
                        for (col in 0 until tic.boardHeight) {
                            stackpane {
                                fitToParentSize()
                                onMouseClicked = EventHandler {
                                    controller.selectNode(row, col, tic)
                                }
                                rectangle {
                                    heightProperty().bind(this@stackpane.heightProperty())
                                    widthProperty().bind(this@stackpane.widthProperty())
                                    fill = Color.rgb(242, 242, 242)
                                    stroke = Color.rgb(141, 160, 189)
                                    strokeType = StrokeType.OUTSIDE
                                    strokeWidth = 0.3
                                }
                                imageview {
                                    fitHeightProperty().bind(this@stackpane.heightProperty().multiply(0.5))
                                    fitWidthProperty().bind(this@stackpane.widthProperty().multiply(0.5))
                                }
                            }
                        }
                    }
                }
                for (i in 0 until tic.boardHeight) {
                    constraintsForColumn(i).percentWidth = tic.boardHeight.toDouble()

                }
                for (i in 0 until tic.boardWidth) {
                    constraintsForRow(i).percentHeight = tic.boardWidth.toDouble()

                }
            }
        }
    }

    init {
        title = "Tic Tac Toe"
        primaryStage.width = 800.0
        primaryStage.height = 1000.0
        primaryStage.isResizable = false
    }
}



