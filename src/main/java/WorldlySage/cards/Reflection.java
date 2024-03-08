package WorldlySage.cards;

import WorldlySage.actions.DoAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.patches.EnterCardGroupPatches;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Reflection extends AbstractEasyCard implements EnterCardGroupPatches.OnEnterCardGroupCard {
    public final static String ID = makeID(Reflection.class.getSimpleName());
    private CardGroup lastGroup;
    private static boolean cease;

    public Reflection() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = block = 4;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        /*addToBot(new ExhaustByPredAction(Wiz.adp().hand, 1, c -> true, new AbstractGameAction() {
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
        }));*/
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }

    @Override
    public void onEnter(CardGroup g) {
        if (g != lastGroup) {
            lastGroup = g;
            if (g == Wiz.adp().hand && !cease) {
                superFlash();
                cease = true;
                Wiz.makeInHand(makeStatEquivalentCopy());
                addToBot(new DoAction(() -> cease = false));
            }
        }
    }
}