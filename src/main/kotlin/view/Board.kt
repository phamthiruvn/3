package view

import javafx.event.EventHandler
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeType
import model.TicBoard
import tornadofx.*
import controller.Controller

class Board : View() {
    var grid: GridPane? = null
    private var tic = TicBoard()
    private val boardSize = tic.boardHeight - 1
    private val controller = Controller()

    override val root = stackpane {

        grid = gridpane {
            maxHeightProperty().bind(this@stackpane.widthProperty())
            maxWidthProperty().bind(this@stackpane.heightProperty())

            prefHeightProperty().bind(this@stackpane.heightProperty())
            prefWidthProperty().bind(this@stackpane.widthProperty())

            for (row in 0..boardSize) {
                row {
                    for (col in 0..boardSize) {
                        val color = Color.WHITESMOKE
                        stackpane {
                            fitToParentSize()
                            onMouseClicked = EventHandler {
                                controller.selectNode(row, col, grid)
                            }
                            rectangle {
                                heightProperty().bind(this@stackpane.heightProperty())
                                widthProperty().bind(this@stackpane.widthProperty())

                                fill = color
                                stroke = Color.BLACK
                                strokeType = StrokeType.OUTSIDE
                                strokeWidth = 0.3
                            }
                            imageview {
                                fitHeightProperty().bind(this@stackpane.heightProperty())
                                fitWidthProperty().bind(this@stackpane.widthProperty())
                            }
                        }
                    }
                }
            }

            for (i in 0..boardSize) {
                constraintsForRow(i).percentHeight = 12.5
                constraintsForColumn(i).percentWidth = 12.5
            }
        }
    }
}


