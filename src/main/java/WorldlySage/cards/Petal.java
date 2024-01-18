package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.BracedPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.InnerPeace;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Petal extends AbstractEasyCard {
    public final static String ID = makeID(Petal.class.getSimpleName());

    public Petal() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        //baseBlock = block = 3;
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new BracedPower(p, magicNumber));
    }

    @Override
    public void upp() {
        //upgradeBlock(2);
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return InnerPeace.ID;
    }
}