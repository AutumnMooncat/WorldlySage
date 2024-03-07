package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.patches.EnterCardGroupPatches;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class NightWatch extends AbstractEasyCard implements EnterCardGroupPatches.OnEnterCardGroupCard {
    public final static String ID = makeID(NightWatch.class.getSimpleName());
    private CardGroup lastGroup;

    public NightWatch() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }

    @Override
    public void onEnter(CardGroup g) {
        if (g != lastGroup) {
            lastGroup = g;
            if (g == Wiz.adp().hand) {
                superFlash();
                addToTop(new GainEnergyAction(magicNumber));
            }
        }
    }
}