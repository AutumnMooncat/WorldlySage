package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.DruidryPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Druidry extends AbstractEasyCard {
    public final static String ID = makeID(Druidry.class.getSimpleName());

    public Druidry() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new DruidryPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return DualWield.ID;
    }
}