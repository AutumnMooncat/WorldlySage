package WorldlySage.actions;

import WorldlySage.cardmods.AbstractGlyph;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ApplyGlyphToRandomCardsAction extends AbstractGameAction {
    private final AbstractGlyph glyph;
    private final CardGroup group;
    private final Predicate<AbstractCard> filter;

    public ApplyGlyphToRandomCardsAction(int cards, AbstractGlyph glyph, CardGroup group) {
        this(cards, glyph, group, c -> c.cost != -2);
    }

    public ApplyGlyphToRandomCardsAction(int cards, AbstractGlyph glyph, CardGroup group, Predicate<AbstractCard> filter) {
        this.amount = cards;
        this.glyph = glyph;
        this.group = group;
        this.filter = filter;
    }

    @Override
    public void update() {
        this.isDone = true;
        ArrayList<AbstractCard> validCards = group.group.stream().filter(filter).collect(Collectors.toCollection(ArrayList::new));
        if (!validCards.isEmpty()) {
            while (amount >= validCards.size()) {
                amount -= validCards.size();
                for (AbstractCard c : validCards) {
                    ApplyGlyphAction.applyGlyph(c, (AbstractGlyph) glyph.makeCopy());
                }
            }
            for (int i = 0 ; i < amount ; i++) {
                AbstractCard c = Wiz.getRandomItem(validCards);
                validCards.remove(c);
                ApplyGlyphAction.applyGlyph(c, (AbstractGlyph) glyph.makeCopy());
            }
        }
    }
}
