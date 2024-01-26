package WorldlySage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import java.util.function.Consumer;

public class AttackDamageRandomEnemyFollowupAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AbstractGameAction.AttackEffect effect;
    private final Consumer<AbstractCreature> followup;
    public AttackDamageRandomEnemyFollowupAction(AbstractCard card, AbstractGameAction.AttackEffect effect, Consumer<AbstractCreature> followup) {
        this.card = card;
        this.effect = effect;
        this.followup = followup;
    }

    @Override
    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.card.calculateCardDamage((AbstractMonster)this.target);
            if (AttackEffect.LIGHTNING == this.effect) {
                this.addToTop(new DamageFollowupAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.NONE, true, followup));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(this.target.hb.cX, this.target.hb.cY)));
            } else {
                this.addToTop(new DamageFollowupAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), this.effect, true, followup));
            }
        }
        this.isDone = true;
    }
}
