package WorldlySage.actions;

import WorldlySage.cardmods.AbstractGlyph;
import WorldlySage.cardmods.CostSwapGlyph;
import WorldlySage.cards.interfaces.OnGlyphCard;
import WorldlySage.powers.interfaces.OnGlyphPower;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ApplyGlyphAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AbstractGlyph glyph;

    public ApplyGlyphAction(AbstractCard card, AbstractGlyph glyph) {
        this.card = card;
        this.glyph = glyph;
    }

    @Override
    public void update() {
        isDone = true;
        applyGlyph(card, glyph);
    }

    public static void applyGlyph(AbstractCard card, AbstractGlyph glyph) {
        int amount = glyph.amount;
        int base = amount;
        if (card instanceof OnGlyphCard) {
            amount = ((OnGlyphCard) card).onGlyphApplied(amount);
        }
        for (AbstractPower p : Wiz.adp().powers) {
            if (p instanceof OnGlyphPower) {
                amount = ((OnGlyphPower) p).OnGlyphApplied(card, amount);
            }
        }
        if (amount != 0) {
            glyph.amount = amount;
            int delta = amount - base;
            if (glyph instanceof CostSwapGlyph) {
                ((CostSwapGlyph) glyph).fixArray(delta);
            }
            card.superFlash();
            CardModifierManager.addModifier(card, glyph);
        }
    }
}
