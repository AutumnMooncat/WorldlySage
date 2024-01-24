package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractAbilityCard;
import WorldlySage.vfx.BigExplosionVFX;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Nova extends AbstractAbilityCard {
    public final static String ID = makeID(Nova.class.getSimpleName());

    public Nova() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new BigExplosionVFX(m));
            dmg(m, AbstractGameAction.AttackEffect.NONE);
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new MakeTempCardInDrawPileAction(makeSameInstanceOf(), 1, false, true, false));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}