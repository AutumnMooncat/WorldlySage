package WorldlySage.cutStuff.cards;

import WorldlySage.actions.DoAction;
import WorldlySage.actions.ExhaustByPredAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Etherealize extends AbstractEasyCard {
    public final static String ID = makeID(Etherealize.class.getSimpleName());

    public Etherealize() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseBlock = block = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustByPredAction(p.hand, magicNumber, true, c -> true, new DoAction(() -> {
            for (AbstractCard ignored : ExhaustByPredAction.exhaustedCards) {
                /*AbstractCard copy = c.makeStatEquivalentCopy();
                CardModifierManager.addModifier(copy, new PhantomMod());
                addToTop(new MakeTempCardInHandAction(copy, magicNumber));*/
                addToTop(new GainBlockAction(p, block));
            }
        })));
    }

    @Override
    public void upp() {
        //upgradeBaseCost(0);
        upgradeMagicNumber(1);
        //upgradeBlock(2);
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}