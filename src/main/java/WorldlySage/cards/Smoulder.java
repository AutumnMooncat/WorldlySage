package WorldlySage.cards;

import WorldlySage.actions.ExhaustByPredAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

import static WorldlySage.MainModfile.makeID;

public class Smoulder extends AbstractEasyCard {
    public final static String ID = makeID(Smoulder.class.getSimpleName());

    public Smoulder() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ExhaustByPredAction(p.hand, magicNumber, true, c -> true, new AbstractGameAction() {
            @Override
            public void update() {
                if (!ExhaustByPredAction.exhaustedCards.isEmpty()) {
                    Wiz.applyToSelfTop(new EnergizedBluePower(p, ExhaustByPredAction.exhaustedCards.size()));
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
        return BladeDance.ID;
    }
}