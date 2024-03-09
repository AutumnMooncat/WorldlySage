package WorldlySage.cards;

import WorldlySage.actions.ApplyGrowthToAllCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.ui.PlantedCardManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Till extends AbstractEasyCard {
    public final static String ID = makeID(Till.class.getSimpleName());

    public Till() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = damage = 3;
        baseMagicNumber = magicNumber = 2;
        //cardsToPreview = new Sapling();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        /*CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0 ; i < magicNumber ; i++) {
            group.addToTop(new Sapling());
        }
        addToBot(new PlantCardsAction(group, group.size()));*/
        addToBot(new ApplyGrowthToAllCardsAction(magicNumber, PlantedCardManager.cards::contains));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
        //upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}