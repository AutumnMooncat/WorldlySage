package WorldlySage.powers;

import WorldlySage.MainModfile;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TabulaRasaPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(TabulaRasaPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean triggered = true;

    public TabulaRasaPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("book");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atStartOfTurn() {
        triggered = false;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (!card.freeToPlayOnce && !triggered) {
            triggered = true;
            int cards = card.costForTurn;
            if (card.cost == -1) {
                cards = card.energyOnUse;
            }
            if (cards > 0) {
                flash();
                addToBot(new DrawCardAction(cards * amount));
            }
        }
    }
}