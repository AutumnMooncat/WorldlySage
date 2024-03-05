package WorldlySage.powers;

import WorldlySage.MainModfile;
import WorldlySage.actions.DoAction;
import WorldlySage.patches.SandstormPatch;
import WorldlySage.vfx.ColoredSmokeBombEffect;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;

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

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && !power.ID.equals(GainStrengthPower.POWER_ID) && source == this.owner && target != this.owner && !target.hasPower(ArtifactPower.POWER_ID)) {
            //Don't infinite loop with Wave of the Hand
            flash();
            addToBot(new DoAction(() -> SandstormPatch.looping = true));
            addToBot(new GainBlockAction(this.owner, this.amount, Settings.FAST_MODE));
            addToBot(new VFXAction(new ColoredSmokeBombEffect(owner.hb.cX, owner.hb.cY, Color.BROWN.cpy())));
            addToBot(new DoAction(() -> SandstormPatch.looping = false));
        }
    }

    /*public void onExhaust(AbstractCard card) {
        flashWithoutSound();
        addToBot(new GainBlockAction(this.owner, this.amount, Settings.FAST_MODE));
        addToBot(new VFXAction(new ColoredSmokeBombEffect(owner.hb.cX, owner.hb.cY, Color.BROWN.cpy())));
    }*/

    /*@Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new DamageRandomEnemyFollowupAction(new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT, mon -> {
            if (mon != null) {
                AbstractDungeon.effectsQueue.add(new ColoredSmokeBombEffect(mon.hb.cX, mon.hb.cY, Color.BROWN.cpy()));
            }
        }));
    }*/

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