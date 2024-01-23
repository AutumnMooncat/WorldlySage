package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static WorldlySage.MainModfile.makeID;

public class TreeCover extends AbstractEasyCard {
    public final static String ID = makeID(TreeCover.class.getSimpleName());

    public TreeCover() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new WeakPower(mon, magicNumber, false)));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}