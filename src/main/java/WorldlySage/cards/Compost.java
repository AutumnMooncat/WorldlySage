package WorldlySage.cards;

import WorldlySage.actions.PlantCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Compost extends AbstractEasyCard {
    public final static String ID = makeID(Compost.class.getSimpleName());

    public Compost() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Fertilizer();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustAction(1, false, false));
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToTop(new Fertilizer());
        addToBot(new PlantCardsAction(group, group.size()));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}