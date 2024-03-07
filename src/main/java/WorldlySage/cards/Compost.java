package WorldlySage.cards;

import WorldlySage.actions.ApplyGrowthToAllCardsAction;
import WorldlySage.actions.PlantCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.ui.PlantedCardManager;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Compost extends AbstractEasyCard {
    public final static String ID = makeID(Compost.class.getSimpleName());

    public Compost() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        cardsToPreview = new Fertilizer();
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new ExhaustAction(1, false, false));
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToTop(new Fertilizer());
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