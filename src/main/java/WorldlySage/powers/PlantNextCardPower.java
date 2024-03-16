package WorldlySage.powers;

import WorldlySage.MainModfile;
import WorldlySage.patches.UseCardActionPatches;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PlantNextCardPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(PlantNextCardPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justEvoked = true;

    public PlantNextCardPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("rebound");
        updateDescription();
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (justEvoked) {
            justEvoked = false;
        } else if (!card.purgeOnUse) {
            if (card.type != AbstractCard.CardType.POWER) {
                flash();
                UseCardActionPatches.PlantField.plantNextCard.set(action, true);
            }
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }
}