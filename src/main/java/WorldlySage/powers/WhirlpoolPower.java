package WorldlySage.powers;

import WorldlySage.MainModfile;
import WorldlySage.actions.DoAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class WhirlpoolPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(WhirlpoolPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean active = true;

    public WhirlpoolPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("forcefield");
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new DoAction(() -> active = false));
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new DoAction(() -> active = true));
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (active) {
            flashWithoutSound();
            addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        }
    }

    /*@Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (!AbstractDungeon.player.hand.group.isEmpty()) {
            flash();
            addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(amount * AbstractDungeon.player.hand.size(), true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        }
    }*/

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}