package WorldlySage.actions;

import WorldlySage.cardmods.AbstractGlyph;
import WorldlySage.cardmods.ShieldGlyph;
import WorldlySage.cards.TheMountain;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ApplyGlyphToAllCardsAction extends AbstractGameAction {
    private final AbstractGlyph glyph;

    public ApplyGlyphToAllCardsAction(AbstractGlyph glyph) {
        this.glyph = glyph;
    }

    @Override
    public void update() {
        this.isDone = true;
        for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true, true)) {
            ApplyGlyphAction.applyGlyph(c, (AbstractGlyph) glyph.makeCopy());
        }
    }
}
