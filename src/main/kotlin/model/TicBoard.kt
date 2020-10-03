package model

import javafx.scene.control.Label
import javafx.scene.layout.GridPane


class TicBoard {
    val boardHeight = 10
    var grid = GridPane()
    val boardWidth = 10
    var winLine = setOf<Pair<Int, Int>>()
    var boardSqu = Array(boardHeight) { arrayOfNulls<Boolean?>(boardWidth) }

    fun resetBoard() {
        boardSqu = Array(boardHeight) { arrayOfNulls<Boolean?>(boardWidth) }
        winLine = setOf<Pair<Int, Int>>()
    }
    fun isEnd(row: Int, col: Int): Int {
        var r = 0
        var c = 0
        val player = TicPlayer()
        val line = mutableSetOf<Pair<Int, Int>>()
        var type = true
        line.add(Pair(row, col))
        if (boardSqu[row][col] != null) {
            type = boardSqu[row][col]!!
            player.typeX = type
        }
        while (c < boardWidth - 4) {
            player.win = true
            for (i in 0..4) {
                if (boardSqu[row][c + i] == null) {
                    player.win = false
                    break
                } else {
                    line.add(Pair(row, c + i))
                    if (boardSqu[row][c + i] != type) {
                        player.win = false
                        line.remove(Pair(row, c + i))
                    }
                }
            }
            if (player.win) {
                winLine = line
                return if (player.typeX) 1
                else 2
            }
            c++
        }

        while (r < boardHeight - 4) {
            player.win = true
            for (i in 0..4) {
                if (boardSqu[r + i][col] == null) {
                    player.win = false
                    break
                } else {
                    line.add(Pair(r + i, col))
                    if (boardSqu[r + i][col] != type) {
                        player.win = false
                        line.remove(Pair(r + i, col))
                    }
                }
            }
            if (player.win) {
                winLine = line
                return if (player.typeX) 1
                else 2
            }
            r++
        }

        r = row
        c = col
        while (r > 0 && c > 0) {
            r--
            c--
        }
        while (r < boardHeight - 4 && c < boardWidth - 4) {
            player.win = true
            for (i in 0..4) {
                if (boardSqu[r + i][c + i] == null) {
                    player.win = false
                    break
                } else {
                    line.add(Pair(r + i, c + i))
                    if (boardSqu[r + i][c + i] != type) {
                        player.win = false
                        line.remove(Pair(r + i, c + i))
                    }
                }
            }
            if (player.win) {
                winLine = line
                return if (player.typeX) 1
                else 2
            }
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
            player.win = true
            for (i in 0..4) {
                if (boardSqu[r - i][c + i] == null) {
                    player.win = false
                    break
                } else {
                    line.add(Pair(r - i, c + i))
                    if (boardSqu[r - i][c + i] != type) {
                        player.win = false
                        line.remove(Pair(r - i, c + i))
                    }
                }
            }
            if (player.win) {
                winLine = line
                return if (player.typeX) 1
                else 2
            }
            r--
            c++
        }
        return 0
    }

}