package WorldlySage.cards;

import WorldlySage.cardmods.DrawGlyph;
import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.MysticForcePower;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Haste extends AbstractEasyCard {
    public final static String ID = makeID(Haste.class.getSimpleName());

    public Haste() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new MysticForcePower(p, magicNumber));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new DrawGlyph(1));
        CardModifierManager.addModifier(this, new EnergyGlyph(1));
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}