package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.CrushPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class BoulderCrash extends AbstractEasyCard {
    public final static String ID = makeID(BoulderCrash.class.getSimpleName());

    public BoulderCrash() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 13;
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        Wiz.applyToEnemy(m, new CrushPower(m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}