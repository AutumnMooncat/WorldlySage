package WorldlySage.cards;

import WorldlySage.actions.ApplyGrowthToAllCardsAction;
import WorldlySage.actions.PlantCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.ui.PlantedCardManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class GrowingPains extends AbstractEasyCard {
    public final static String ID = makeID(GrowingPains.class.getSimpleName());

    public GrowingPains() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Shiv();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0 ; i < 2 ; i++) {
            AbstractCard card = new Shiv();
            group.addToTop(card);
        }
        addToBot(new PlantCardsAction(group, group.size()));
        addToBot(new ApplyGrowthToAllCardsAction(magicNumber, PlantedCardManager.cards::contains));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}