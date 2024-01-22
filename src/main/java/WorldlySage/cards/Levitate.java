package WorldlySage.cards;

import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.LevitatePower;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Levitate extends AbstractEasyCard {
    public final static String ID = makeID(Levitate.class.getSimpleName());

    public Levitate() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new LevitatePower(p, magicNumber));
    }

    @Override
    public void upp() {
        //upgradeBaseCost(0);
        CardModifierManager.addModifier(this, new EnergyGlyph(1));
    }

    @Override
    public String cardArtCopy() {
        return DualWield.ID;
    }
}