package WorldlySage.powers;

import WorldlySage.MainModfile;
import WorldlySage.actions.ApplyGrowthAction;
import WorldlySage.ui.PlantedCardManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HydrationPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(HydrationPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HydrationPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("skillBurn");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();
        for (AbstractCard c : PlantedCardManager.cards.group) {
            ApplyGrowthAction.applyGrowth(c, amount);
        }
    }

    /*@Override
    public int onGrow(AbstractCard card, int amount) {
        return amount + this.amount;
    }*/
}