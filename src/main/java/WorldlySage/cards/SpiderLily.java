package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.DeathtouchedPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class SpiderLily extends AbstractEasyCard {
    public final static String ID = makeID(SpiderLily.class.getSimpleName());

    public SpiderLily() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new DeathtouchedPower(m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        //upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}