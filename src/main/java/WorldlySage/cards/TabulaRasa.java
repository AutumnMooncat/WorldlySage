package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.TabulaRasaPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class TabulaRasa extends AbstractEasyCard {
    public final static String ID = makeID(TabulaRasa.class.getSimpleName());

    public TabulaRasa() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new TabulaRasaPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }

    @Override
    public String cardArtCopy() {
        return DualWield.ID;
    }
}