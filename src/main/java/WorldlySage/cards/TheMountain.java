package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphToAllCardsAction;
import WorldlySage.cardmods.ShieldGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class TheMountain extends AbstractEasyCard {
    public final static String ID = makeID(TheMountain.class.getSimpleName());

    public TheMountain() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyGlyphToAllCardsAction(new ShieldGlyph(magicNumber)));
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }

}