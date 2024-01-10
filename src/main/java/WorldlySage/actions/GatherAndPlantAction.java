package WorldlySage.actions;

import WorldlySage.ui.PlantedCardManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GatherAndPlantAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private final boolean upgradedCards;
    private final boolean freeThisTurn;
    private final Predicate<AbstractCard> filter;

    public GatherAndPlantAction(Predicate<AbstractCard> filter) {
        this(3, filter, false, false);
    }

    public GatherAndPlantAction(int amount, Predicate<AbstractCard> filter, boolean freeThisTurn, boolean upgradedCards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgradedCards = upgradedCards;
        this.freeThisTurn = freeThisTurn;
        this.filter = filter;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(this.generateCardChoices(), CardRewardScreen.TEXT[1], true);
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard chosenCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (this.freeThisTurn) {
                        chosenCard.setCostForTurn(0);
                    }

                    chosenCard.current_x = -1000.0F * Settings.xScale;
                    PlantedCardManager.addCard(chosenCard);

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

        }
        this.tickDuration();
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> validCards = new ArrayList<>();
        validCards.addAll(AbstractDungeon.srcCommonCardPool.group.stream().filter(filter).collect(Collectors.toList()));
        validCards.addAll(AbstractDungeon.srcUncommonCardPool.group.stream().filter(filter).collect(Collectors.toList()));
        validCards.addAll(AbstractDungeon.srcRareCardPool.group.stream().filter(filter).collect(Collectors.toList()));
        if (validCards.isEmpty()) {
            //We probably got the card off character without prismatic shard, just check all cards instead
            validCards.addAll(CardLibrary.getAllCards().stream().filter(c -> filter.test(c) && (c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE)).collect(Collectors.toList()));
        }
        ArrayList<AbstractCard> ret = new ArrayList<>();
        for (int i = 0 ; (i < amount && !validCards.isEmpty()) ; i++) {
            ret.add(validCards.remove(AbstractDungeon.cardRandomRng.random(validCards.size() - 1)).makeCopy());
        }
        if (upgradedCards) {
            for (AbstractCard c : ret) {
                if (c.canUpgrade()) {
                    c.upgrade();
                }
            }
        }
        return ret;
    }
}