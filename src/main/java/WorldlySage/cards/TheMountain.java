package WorldlySage.cards;

import WorldlySage.actions.ActuallyWaitAction;
import WorldlySage.actions.ModifyMagicAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.patches.EnterCardGroupPatches;
import WorldlySage.powers.CrushPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static WorldlySage.MainModfile.makeID;

public class TheMountain extends AbstractEasyCard implements EnterCardGroupPatches.OnEnterCardGroupCard {
    public final static String ID = makeID(TheMountain.class.getSimpleName());
    private CardGroup lastGroup;

    public TheMountain() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }
        addToBot(new ActuallyWaitAction(0.8F));
        Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        Wiz.applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        Wiz.applyToEnemy(m, new CrushPower(m, magicNumber));
    }

    @Override
    public void upp() {
        //upgradeBaseCost(3);
        //upgradeMagicNumber(2);
        upgradeSecondMagic(1);
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
                addToTop(new ModifyMagicAction(uuid, secondMagic));
            }
        }
    }
}