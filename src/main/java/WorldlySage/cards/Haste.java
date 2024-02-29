package WorldlySage.cards;

import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cardmods.PhantomMod;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Haste extends AbstractEasyCard {
    public final static String ID = makeID(Haste.class.getSimpleName());

    public Haste() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card = Wiz.secondLastCardPlayed();
        if (card != null) {
            card = card.makeStatEquivalentCopy();
            CardModifierManager.addModifier(card, new PhantomMod());
            addToBot(new MakeTempCardInHandAction(card));
        }
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