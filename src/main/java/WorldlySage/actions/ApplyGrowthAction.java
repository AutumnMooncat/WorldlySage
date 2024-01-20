package WorldlySage.actions;

import WorldlySage.cardmods.GrowthMod;
import WorldlySage.cards.interfaces.OnGrowthCard;
import WorldlySage.powers.interfaces.OnGrowthPower;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ApplyGrowthAction extends AbstractGameAction {
    private AbstractCard card;

    public ApplyGrowthAction(AbstractCard card, int amount) {
        this.amount = amount;
        this.card = card;
    }

    @Override
    public void update() {
        isDone = true;
        applyGrowth(card, amount);
    }

    public static void applyGrowth(AbstractCard card, int amount) {
        if (card instanceof OnGrowthCard) {
            amount = ((OnGrowthCard) card).onGrow(amount);
        }
        for (AbstractPower p : Wiz.adp().powers) {
            if (p instanceof OnGrowthPower) {
                amount = ((OnGrowthPower) p).onGrow(card, amount);
            }
        }
        card.superFlash();
        CardModifierManager.addModifier(card, new GrowthMod(amount));
        card.applyPowers();
    }
}
