package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.damageMods.TempPiercingDamage;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static WorldlySage.MainModfile.makeID;

public class PiercingGlyph extends AbstractGlyph {
    public static String ID = makeID(PiercingGlyph.class.getSimpleName());
    public static Texture ICON = TexLoader.getTexture(MainModfile.makeImagePath("icons/drill.png"));
    private final TempPiercingDamage tpd = new TempPiercingDamage();

    public PiercingGlyph(int amount) {
        super(ID, amount, ICON, KeywordManager.PIERCE_GLYPH);
    }

    @Override
    public void extraEffect(AbstractCard card, AbstractCreature target, UseCardAction action) {}

    @Override
    public void onInitialApplication(AbstractCard card) {
        DamageModifierManager.addModifier(card, tpd);
    }

    @Override
    public void onRemove(AbstractCard card) {
        DamageModifierManager.removeModifier(card, tpd);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PiercingGlyph(amount);
    }
}
