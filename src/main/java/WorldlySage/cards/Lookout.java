package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphToRandomCardsAction;
import WorldlySage.cardmods.ShieldGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.purple.InnerPeace;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Lookout extends AbstractEasyCard {
    public final static String ID = makeID(Lookout.class.getSimpleName());

    public Lookout() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyGlyphToRandomCardsAction(magicNumber, new ShieldGlyph(1), p.hand));
    }

    @Override
    public void upp() {
        //upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return InnerPeace.ID;
    }
}