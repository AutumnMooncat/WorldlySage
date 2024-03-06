package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Exploration extends AbstractEasyCard {
    public final static String ID = makeID(Exploration.class.getSimpleName());

    public Exploration() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                int zeroes = 0;
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    if (c.costForTurn == 0 || c.freeToPlayOnce) {
                        zeroes++;
                    }
                }
                if (zeroes > 0) {
                    addToTop(new DrawCardAction(zeroes));
                }
                this.isDone = true;
            }
        }));
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