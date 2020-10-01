package controller

import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import model.TicBoard
import model.TicPlayer

class Controller {
    private var player = TicPlayer(true, "X")
    private var tic = TicBoard()
    private val boardSize = tic.boardHeight - 1

    fun newBoard(grid: GridPane?) {
        player = TicPlayer(true, "X")
        tic.resetBoard()
        for (row in 0..boardSize) {
            for (col in 0..boardSize) {
                tic.boardSqu[row][col] = null
                val node = getNode(row, col, grid)
                if (node is StackPane) {
                    for (children in node.children) {
                        if (children is ImageView) {
                            children.image = null
                        }
                    }
                }
            }
        }
    }

    fun selectNode(row: Int, col: Int, grid: GridPane?) {
        tic.boardSqu[row][col] = player.typeX
        val selected = getNode(row, col, grid) as StackPane
        for (node in selected.children) {
            if (node is ImageView && node.image == null) {
                if (player.typeX) node.image = Image("X.png")
                else node.image = Image("O.png")
                player.typeX = !player.typeX
            }
        }
        val resultGame = tic.isEnd(row, col)
        if (tic.isEnd(row, col) != 0) {
            println("Player$resultGame")
        }
    }

    private fun getNode(row: Int, column: Int, grid: GridPane?): Node? {
        var result: Node? = null
        val childrens = grid!!.children
        for (node in childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node
                break
            }
        }
        return result
    }

}