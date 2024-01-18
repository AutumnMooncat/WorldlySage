package WorldlySage.cards;

import WorldlySage.actions.ScaleAllByPredAction;
import WorldlySage.cards.abstracts.AbstractAbilityCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Fireball extends AbstractAbilityCard {
    public final static String ID = makeID(Fireball.class.getSimpleName());

    public Fireball() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 3;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new ScaleAllByPredAction(this, magicNumber, ScaleAllByPredAction.StatBoost.DAMAGE, c -> c instanceof Fireball));
    }

    @Override
    public void upp() {
        //upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}