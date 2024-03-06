package WorldlySage.cards;

import WorldlySage.actions.ActuallyWaitAction;
import WorldlySage.actions.DamageFollowupAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.vfx.BigExplosionVFX;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static WorldlySage.MainModfile.makeID;

public class Meteor extends AbstractEasyCard {
    public final static String ID = makeID(Meteor.class.getSimpleName());

    public Meteor() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 20;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }
        addToBot(new ActuallyWaitAction(0.8F));
        addToBot(new DamageFollowupAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, false, t -> {
            if (t.isDying || t.currentHealth <= 0) {
                addToTop(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
                addToTop(new BigExplosionVFX(t));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(8);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}