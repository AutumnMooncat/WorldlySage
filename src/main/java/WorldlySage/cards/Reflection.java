package WorldlySage.cards;

import WorldlySage.actions.ExhaustByPredAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.HydrationPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static WorldlySage.MainModfile.makeID;

public class Reflection extends AbstractEasyCard {
    public final static String ID = makeID(Reflection.class.getSimpleName());

    public Reflection() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustByPredAction(Wiz.adp().hand, 1, c -> true, new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : ExhaustByPredAction.exhaustedCards) {
                    if (c.costForTurn == -1) {
                        Wiz.applyToSelfTop(new HydrationPower(p, EnergyPanel.getCurrentEnergy()));
                    } else if (c.costForTurn > 0) {
                        Wiz.applyToSelfTop(new HydrationPower(p, c.costForTurn));
                    }
                }
                this.isDone = true;
            }
        }));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}