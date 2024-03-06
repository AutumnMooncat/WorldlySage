package WorldlySage.powers;

import WorldlySage.MainModfile;
import WorldlySage.patches.EnergyGainPatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LevitatePower extends AbstractPower implements EnergyGainPatch.OnGainEnergyPower {
    public static final String POWER_ID = MainModfile.makeID(LevitatePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LevitatePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("flight");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public int onGainEnergy(int energyAmount) {
        flash();
        //addToBot(new LevitateAction(amount));
        addToBot(new GainBlockAction(owner, owner, amount));
        return energyAmount;
    }
}