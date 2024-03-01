package WorldlySage.cards;

import WorldlySage.actions.DoAction;
import WorldlySage.actions.ExhaustByPredAction;
import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cardmods.PhantomMod;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Etherealize extends AbstractEasyCard {
    public final static String ID = makeID(Etherealize.class.getSimpleName());

    public Etherealize() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustByPredAction(p.hand, 1, c -> true, new DoAction(() -> {
            for (AbstractCard c : ExhaustByPredAction.exhaustedCards) {
                AbstractCard copy = c.makeStatEquivalentCopy();
                CardModifierManager.addModifier(copy, new PhantomMod());
                addToTop(new MakeTempCardInHandAction(copy, magicNumber));
            }
        })));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new EnergyGlyph(1));
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}