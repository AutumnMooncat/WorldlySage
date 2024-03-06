package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.GrowNextCardPower;
import WorldlySage.powers.PlantNextCardPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.InnerPeace;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class GrassyPlain extends AbstractEasyCard {
    public final static String ID = makeID(GrassyPlain.class.getSimpleName());

    public GrassyPlain() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = block = 3;
        baseMagicNumber = magicNumber = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new PlantNextCardPower(p, 1));
        if (magicNumber > 0) {
            Wiz.applyToSelf(new GrowNextCardPower(p, magicNumber));
        }
    }

    @Override
    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(2);
        //upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return InnerPeace.ID;
    }
}