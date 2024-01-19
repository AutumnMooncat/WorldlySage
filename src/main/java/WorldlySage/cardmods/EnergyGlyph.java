package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import WorldlySage.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static WorldlySage.MainModfile.makeID;

public class EnergyGlyph extends AbstractGlyph {
    public static String ID = makeID(EnergyGlyph.class.getSimpleName());
    public static Texture ICON = TexLoader.getTexture(MainModfile.makeImagePath("icons/ball-glow.png"));

    public EnergyGlyph(int amount) {
        super(ID, amount, ICON, KeywordManager.ENERGY_GLYPH);
    }

    @Override
    public void extraEffect(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new GainEnergyAction(1));
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new EnergyGlyph(amount);
    }
}
