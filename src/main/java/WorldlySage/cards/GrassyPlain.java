package WorldlySage.cards;

import WorldlySage.actions.PlantCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.purple.InnerPeace;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class GrassyPlain extends AbstractEasyCard {
    public final static String ID = makeID(GrassyPlain.class.getSimpleName());

    public GrassyPlain() {
        super(ID, 2, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Sapling();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0 ; i < magicNumber ; i++) {
            group.addToTop(new Sapling());
        }
        addToBot(new PlantCardsAction(group, group.size()));

    }

    @Override
    public void upp() {
        //upgradeBlock(2);
        upgradeMagicNumber(1);
        //upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return InnerPeace.ID;
    }
}