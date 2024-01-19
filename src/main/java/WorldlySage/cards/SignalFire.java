package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphToAllCardsAction;
import WorldlySage.cardmods.AccuracyGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class SignalFire extends AbstractEasyCard {
    public final static String ID = makeID(SignalFire.class.getSimpleName());

    public SignalFire() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustAction(1, false, false));
        addToBot(new ApplyGlyphToAllCardsAction(new AccuracyGlyph(1), c -> Wiz.adp().hand.contains(c) && c.type == CardType.ATTACK));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}