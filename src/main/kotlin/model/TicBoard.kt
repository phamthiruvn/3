package model

import controller.Player


class TicBoard() {
    val boardHeight = 20
    val boardWidth = 20
    var boardSqu = Array(boardWidth) { arrayOfNulls<Boolean?>(boardHeight) }

    fun resetBoard() {
        boardSqu = Array(boardWidth) { arrayOfNulls<Boolean?>(boardHeight) }
    }

    fun isEnd(row: Int, col: Int): Int {
        var r = 0
        var c = 0
        val playerX = TicPlayer(true, "X")
        val playerO = TicPlayer(false, "O")
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
            if (playerX.win) return 1
            if (playerO.win) return 2
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
            if (playerX.win) return 1
            if (playerO.win) return 2
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
            if (playerX.win) return 1
            if (playerO.win) return 2
            r--
            c++
        }
        return 0
    }

}