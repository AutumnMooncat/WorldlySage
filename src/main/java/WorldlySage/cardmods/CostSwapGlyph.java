package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;

import static WorldlySage.MainModfile.makeID;

public class CostSwapGlyph extends AbstractGlyph {
    public static String ID = makeID(CostSwapGlyph.class.getSimpleName());
    public static Texture ICON = TexLoader.getTexture(MainModfile.makeImagePath("icons/card-exchange.png"));
    private final ArrayList<Integer> costTracker = new ArrayList<>();
    private final ArrayList<Integer> costForTurnTracker = new ArrayList<>();

    public CostSwapGlyph(int turns, int oldCost, int oldCostForTurn, int newCost, int newCostForTurn) {
        super(ID, turns, ICON, KeywordManager.SWAP_GLYPH);
        costTracker.add(oldCost);
        costForTurnTracker.add(oldCostForTurn);
        for (int i = 0 ; i < turns ; i++) {
            costTracker.add(newCost);
            costForTurnTracker.add(newCostForTurn);
        }
    }

    public CostSwapGlyph(ArrayList<Integer> costTracker, ArrayList<Integer> costForTurnTracker) {
        super(ID, costTracker.size(), ICON, KeywordManager.SWAP_GLYPH);
        this.costTracker.addAll(costTracker);
        this.costForTurnTracker.addAll(costForTurnTracker);
    }

    @Override
    public void extraEffect(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                card.cost = costTracker.remove(costTracker.size()-1);
                card.costForTurn = costForTurnTracker.remove(costForTurnTracker.size()-1);
            }
        });
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.cost = costTracker.remove(costTracker.size()-1);
        card.costForTurn = costForTurnTracker.remove(costForTurnTracker.size()-1);
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, ID)) {
            CostSwapGlyph glyph = (CostSwapGlyph) CardModifierManager.getModifiers(card, ID).get(0);
            glyph.amount += this.amount;
            glyph.costTracker.addAll(this.costTracker);
            glyph.costForTurnTracker.addAll(this.costForTurnTracker);
            glyph.onInitialApplication(card);
            return false;
        }
        return true;
    }

    public void fixArray(int amount) {
        for (int i = 0 ; i < amount ; i++) {
            costTracker.add(costTracker.get(costTracker.size()-1));
            costForTurnTracker.add(costForTurnTracker.get(costForTurnTracker.size()-1));
        }
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CostSwapGlyph(costTracker, costForTurnTracker);
    }
}
