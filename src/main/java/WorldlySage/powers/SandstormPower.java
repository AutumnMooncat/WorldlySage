package WorldlySage.powers;

import WorldlySage.MainModfile;
import WorldlySage.actions.DamageRandomEnemyFollowupAction;
import WorldlySage.vfx.ColoredSmokeBombEffect;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SandstormPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(SandstormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SandstormPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("attackBurn");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new DamageRandomEnemyFollowupAction(new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT, mon -> {
            if (mon != null) {
                AbstractDungeon.effectsQueue.add(new ColoredSmokeBombEffect(mon.hb.cX, mon.hb.cY, Color.BROWN.cpy()));
            }
        }));
    }

    /*@Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.costForTurn == 0 || (card.freeToPlayOnce && card.cost != -1)) {
            flash();
            addToBot(new DamageRandomEnemyFollowupAction(new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT, mon -> {
                if (mon != null) {
                    AbstractDungeon.effectsQueue.add(new ColoredSmokeBombEffect(mon.hb.cX, mon.hb.cY, Color.BROWN.cpy()));
                }
            }));
        }
    }*/
}