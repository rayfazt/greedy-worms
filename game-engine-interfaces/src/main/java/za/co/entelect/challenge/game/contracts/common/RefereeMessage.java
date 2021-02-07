package za.co.entelect.challenge.game.contracts.common;

import java.util.List;

public class RefereeMessage {

    public boolean isValid;
    public List<String> reasons;

    public RefereeMessage(boolean isValid, List<String> reasons) {
        this.isValid = isValid;
        this.reasons = reasons;
    }
}
