package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.SunnyDayPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class SunnyDay extends AbstractEasyCard {
    public final static String ID = makeID(SunnyDay.class.getSimpleName());

    public SunnyDay() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new SunnyDayPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        //upgradeBaseCost(1);
    }

    @Override
    public String cardArtCopy() {
        return DualWield.ID;
    }
}