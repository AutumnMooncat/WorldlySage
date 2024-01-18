package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class WoodWall extends AbstractEasyCard {
    public final static String ID = makeID(WoodWall.class.getSimpleName());

    public WoodWall() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}