package WorldlySage.cards;

import WorldlySage.cardmods.PiercingGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
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
        baseDamage = damage = 18;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }
        this.addToBot(new WaitAction(0.8F));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (Wiz.hasGlyph(this)) {
            damage *= 2;
            isDamageModified = damage != baseDamage;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (Wiz.hasGlyph(this)) {
            damage *= 2;
            isDamageModified = damage != baseDamage;
        }
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new PiercingGlyph(1));
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}