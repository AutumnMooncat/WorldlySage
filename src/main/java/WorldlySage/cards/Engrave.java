package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphAction;
import WorldlySage.actions.BetterSelectCardsInHandAction;
import WorldlySage.cardmods.CostSwapGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Engrave extends AbstractEasyCard {
    public final static String ID = makeID(Engrave.class.getSimpleName());

    public Engrave() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(2, cardStrings.EXTENDED_DESCRIPTION[0], false, false, c -> c.cost >= 0, l -> {
            if (l.size() == 2) {
                AbstractCard card1 = l.get(0);
                AbstractCard card2 = l.get(1);
                addToTop(new ApplyGlyphAction(card1, new CostSwapGlyph(1, card1.cost, card1.costForTurn, card2.cost, card2.costForTurn)));
                addToTop(new ApplyGlyphAction(card2, new CostSwapGlyph(1, card2.cost, card2.costForTurn, card1.cost, card1.costForTurn)));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}