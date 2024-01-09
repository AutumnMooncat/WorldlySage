package WorldlySage.cards;

import WorldlySage.actions.PlantCopyOfCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Bifurcate extends AbstractEasyCard {
    public final static String ID = makeID(Bifurcate.class.getSimpleName());

    public Bifurcate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PlantCopyOfCardsAction(Wiz.adp().hand, 1, magicNumber));
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