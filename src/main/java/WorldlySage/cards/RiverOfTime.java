package WorldlySage.cards;

import WorldlySage.actions.ApplyGrowthAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class RiverOfTime extends AbstractEasyCard {
    public final static String ID = makeID(RiverOfTime.class.getSimpleName());

    public RiverOfTime() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FetchAction(p.discardPile, 1, l -> {
            for (AbstractCard c : l) {
                ApplyGrowthAction.applyGrowth(c, magicNumber);
            }
        }));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}