package WorldlySage.actions;

import WorldlySage.cardmods.ShieldGlyph;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LevitateAction extends AbstractGameAction {
    private final boolean deferred;

    public LevitateAction(int amount) {
        this(amount, false);
    }

    public LevitateAction(int amount, boolean deferred) {
        this.amount = amount;
        this.deferred = deferred;
    }

    @Override
    public void update() {
        if (!deferred) {
            addToBot(new LevitateAction(amount, true));
        } else {
            ArrayList<AbstractCard> validCards = Wiz.adp().hand.group.stream().filter(c -> c.cost != -2).collect(Collectors.toCollection(ArrayList::new));
            for (int i = 0 ; i < amount ; i++) {
                AbstractCard c = Wiz.getRandomItem(validCards);
                if (c != null) {
                    ApplyGlyphAction.applyGlyph(c, new ShieldGlyph(1));
                }
            }
        }
        this.isDone = true;
    }
}
