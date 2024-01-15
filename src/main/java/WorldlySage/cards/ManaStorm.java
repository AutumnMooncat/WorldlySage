package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.ManaStormPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class ManaStorm extends AbstractEasyCard {
    public final static String ID = makeID(ManaStorm.class.getSimpleName());

    public ManaStorm() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ManaStormPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return DualWield.ID;
    }
}