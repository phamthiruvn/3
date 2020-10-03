package model

import javafx.scene.layout.GridPane


class TicBoard {
    val boardHeight = 10
    var grid = GridPane()
    val boardWidth = 10
    var winLine = mutableSetOf<Pair<Int, Int>>()
    var boardSqu = Array(boardHeight) { arrayOfNulls<Boolean?>(boardWidth) }

    fun resetBoard() {
        boardSqu = Array(boardHeight) { arrayOfNulls<Boolean?>(boardWidth) }
        winLine = mutableSetOf<Pair<Int, Int>>()
    }

    fun isEnd(row: Int, col: Int): Int {
        var r = 0
        var c = 0
        var result = 0
        val playerX = TicPlayer()
        val playerO = TicPlayer()

        while (c < boardWidth - 4) {
            playerX.win = true
            playerO.win = true
            for (i in 0..4) {
                if (boardSqu[row][c + i] == null) {
                    playerX.win = false
                    playerO.win = false
                    break
                }
                if (!boardSqu[row][c + i]!!) playerX.win = false
                if (boardSqu[row][c + i]!!) playerO.win = false
            }
            if (playerX.win) return 1
            if (playerO.win) return 2
            c++
        }



        while (r < boardHeight - 4) {
            playerX.win = true
            playerO.win = true
            for (i in 0..4) {
                if (boardSqu[r + i][col] == null) {
                    playerX.win = false
                    playerO.win = false
                    break
                }
                if (!boardSqu[r + i][col]!!) playerX.win = false
                if (boardSqu[r + i][col]!!) playerO.win = false
            }
            if (playerX.win) result = 1
            if (playerO.win) result = 2
            r++
        }

        r = row
        c = col
        while (r > 0 && c > 0) {
            r--
            c--
        }
        while (r < boardHeight - 4 && c < boardWidth - 4) {
            playerX.win = true
            playerO.win = true
            for (i in 0..4) {
                if (boardSqu[r + i][c + i] == null) {
                    playerX.win = false
                    playerO.win = false
                    break
                }
                if (!boardSqu[r + i][c + i]!!) playerX.win = false
                if (boardSqu[r + i][c + i]!!) playerO.win = false
            }
            if (playerX.win) result = 1
            if (playerO.win) result = 2
            r++
            c++
        }

        r = row
        c = col
        while (r < boardHeight - 1 && c > 0) {
            r++
            c--
        }
        while (r >= 4 && c < boardHeight - 4) {
            playerX.win = true
            playerO.win = true
            for (i in 0..4) {
                if (boardSqu[r - i][c + i] == null) {
                    playerX.win = false
                    playerO.win = false
                    break
                }
                if (!boardSqu[r - i][c + i]!!) playerX.win = false
                if (boardSqu[r - i][c + i]!!) playerO.win = false
            }
            if (playerX.win) result = 1
            if (playerO.win) result = 2
            r--
            c++
        }
        return result
    }

}