package za.co.entelect.challenge.game.delegate.referee

import za.co.entelect.challenge.game.contracts.common.RefereeMessage
import za.co.entelect.challenge.game.contracts.game.GameReferee
import za.co.entelect.challenge.game.contracts.map.GameMap
import za.co.entelect.challenge.game.delegate.engine.DelegateMap

class DelegateReferee : GameReferee {

    override fun isMatchValid(map: GameMap): RefereeMessage {
        if (map !is DelegateMap) {
            throw IllegalArgumentException("Unknown Map Class")
        }
        return map.refereeIssues
    }
}
