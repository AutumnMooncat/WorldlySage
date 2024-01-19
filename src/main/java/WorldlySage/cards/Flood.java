package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Flood extends AbstractEasyCard {
    public final static String ID = makeID(Flood.class.getSimpleName());

    public Flood() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExpertiseAction(p, BaseMod.MAX_HAND_SIZE));
    }

    @Override
    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}