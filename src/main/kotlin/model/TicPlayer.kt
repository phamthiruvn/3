package model

import controller.Player

class TicPlayer(override var typeX: Boolean, override val name: String) : Player {
    override var win = false

}