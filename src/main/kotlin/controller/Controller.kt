package controller

import javafx.scene.Node
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import model.TicBoard
import model.TicPlayer
import tornadofx.tag
import kotlin.system.exitProcess

class Controller {
    private var player = TicPlayer()

    fun newBoard(tic: TicBoard) {
        player = TicPlayer()
        tic.resetBoard()
        for (row in 0 until tic.boardWidth) {
            for (col in 0 until tic.boardHeight) {
                val node = getNode(row, col, tic)
                if (node is StackPane) {
                    for (children in node.children) {
                        if (children is ImageView) {
                            children.image = null
                        }
                        if (children is Rectangle) children.fill = Color.rgb(242, 242, 242)
                    }
                }
            }
        }
    }

    fun selectNode(row: Int, col: Int, tic: TicBoard) {
        selectedColor(row, col, tic)
        if (tic.boardSqu[row][col] == null) {
            tic.boardSqu[row][col] = player.typeX
            val selected = getNode(row, col, tic) as StackPane
            for (node in selected.children) {
                if (node is ImageView && node.image == null) {
                    if (player.typeX) node.image = Image("X.png")
                    else node.image = Image("O.png")
                    player.typeX = !player.typeX
                }
            }
        }
        winnerAlert(row, col, tic)
    }

    private fun getNode(row: Int, column: Int, tic: TicBoard): Node? {
        val grid = tic.grid
        var result: Node? = null
        val childrens = grid.children
        for (node in childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node
                break
            }
        }
        return result
    }

    private fun winnerAlert(row: Int, col: Int, tic: TicBoard) {
        val player = tic.isEnd(row, col)
        if (player != 0) {
            for ((row,col) in tic.winLine) {
                val node = getNode(row, col, tic)
                if (node is StackPane) {
                    for (children in node.children) {
                        if (children is Rectangle) children.fill = Color.rgb(250, 232, 177)
                    }
                }
            }
                val alert = Alert(Alert.AlertType.CONFIRMATION)
            alert.title = "Game is end"
            alert.headerText = "Player $player win  "
            alert.contentText = " Want to play again?"
            val buttonTypeYes = ButtonType("Yes", ButtonBar.ButtonData.YES)
            val buttonTypeNo = ButtonType("No", ButtonBar.ButtonData.NO)
            alert.buttonTypes.setAll(buttonTypeYes, buttonTypeNo)

            val result = alert.showAndWait()
            if (result.get() == buttonTypeYes) newBoard(tic)
            else exitProcess(0)
        }

    }


    private fun selectedColor(row: Int, col: Int, tic: TicBoard) {
        for (row in 0 until tic.boardWidth) {
            for (col in 0 until tic.boardHeight) {
                val node = getNode(row, col, tic)
                if (node is StackPane) {
                    for (children in node.children) {
                        if (children is Rectangle) children.fill = Color.rgb(242, 242, 242)
                    }
                }
            }
        }
        val node = getNode(row, col, tic)
        if (node is StackPane) {
            for (children in node.children) {
                if (children is Rectangle) children.fill = Color.rgb(250, 232, 177)
            }
        }
    }

}