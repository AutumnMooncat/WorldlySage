package WorldlySage.actions;

import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.function.Predicate;

public class ApplyGrowthToAllCardsAction extends AbstractGameAction {
    private final Predicate<AbstractCard> filter;

    public ApplyGrowthToAllCardsAction(int amount) {
        this(amount, c -> true);
    }

    public ApplyGrowthToAllCardsAction(int amount, Predicate<AbstractCard> filter) {
        this.amount = amount;
        this.filter = filter;
    }

    @Override
    public void update() {
        this.isDone = true;
        for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true, true)) {
            if (filter.test(c)) {
                ApplyGrowthAction.applyGrowth(c, amount);
            }
        }
    }
}
