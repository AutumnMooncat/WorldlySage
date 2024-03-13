package WorldlySage.cards;

import WorldlySage.actions.ApplyGrowthToAllCardsAction;
import WorldlySage.actions.PlantCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.ui.PlantedCardManager;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.AutoShields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Mending extends AbstractEasyCard {
    public final static String ID = makeID(Mending.class.getSimpleName());

    public Mending() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Sapling();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToTop(new Sapling());
        addToBot(new PlantCardsAction(group, group.size()));
        addToBot(new ApplyGrowthToAllCardsAction(magicNumber, PlantedCardManager.cards::contains));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return AutoShields.ID;
    }
}