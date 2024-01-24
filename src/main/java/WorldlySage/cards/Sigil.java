package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphAction;
import WorldlySage.actions.BetterSelectCardsInHandAction;
import WorldlySage.cardmods.AccuracyGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Sigil extends AbstractEasyCard {
    public final static String ID = makeID(Sigil.class.getSimpleName());

    public Sigil() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0], false, false, c -> c.type == CardType.ATTACK, l -> {
            for (AbstractCard c : l) {
                ApplyGlyphAction.applyGlyph(c, new AccuracyGlyph(1));
            }
        }));
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