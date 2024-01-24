package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractAbilityCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static WorldlySage.MainModfile.makeID;

public class Waterfall extends AbstractAbilityCard {
    public final static String ID = makeID(Waterfall.class.getSimpleName());

    public Waterfall() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new SFXAction("BLUNT_FAST"));
        Wiz.atb(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY + 99f * Settings.scale, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true), 0.15f));
        Wiz.atb(new SFXAction("BLUNT_FAST"));
        Wiz.atb(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY + 66f * Settings.scale, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true), 0.15f));
        Wiz.atb(new SFXAction("BLUNT_FAST"));
        Wiz.atb(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY + 33f * Settings.scale, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true), 0.15f));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void triggerOnExhaust() {
        addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}