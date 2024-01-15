package WorldlySage.cards;

import WorldlySage.actions.GatherAndPlantAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Forage extends AbstractEasyCard {
    public final static String ID = makeID(Forage.class.getSimpleName());

    public Forage() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GatherAndPlantAction(magicNumber, c -> true, false, upgraded));
    }

    @Override
    public void upp() {
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}