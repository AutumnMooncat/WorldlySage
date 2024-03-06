package WorldlySage.actions;

import com.megacrit.cardcrawl.actions.utility.WaitAction;

public class ActuallyWaitAction extends WaitAction {

    public ActuallyWaitAction(float setDur) {
        super(setDur);
        this.duration = setDur;
    }
}
