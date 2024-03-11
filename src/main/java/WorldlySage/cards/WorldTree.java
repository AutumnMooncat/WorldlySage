package WorldlySage.cards;

import WorldlySage.actions.ApplyGrowthAction;
import WorldlySage.actions.ApplyGrowthToAllCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class WorldTree extends AbstractEasyCard {
    public final static String ID = makeID(WorldTree.class.getSimpleName());

    public WorldTree() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyGrowthToAllCardsAction(magicNumber));
        addToBot(new ApplyGrowthAction(this, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}