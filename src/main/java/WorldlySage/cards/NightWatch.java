package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphToRandomCardsAction;
import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class NightWatch extends AbstractEasyCard {
    public final static String ID = makeID(NightWatch.class.getSimpleName());

    public NightWatch() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyGlyphToRandomCardsAction(magicNumber, new EnergyGlyph(1), p.hand));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}