package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class AccuracyGlyph extends AbstractGlyph {
    public static String ID = makeID(AccuracyGlyph.class.getSimpleName());
    public static Texture ICON = TexLoader.getTexture(MainModfile.makeImagePath("icons/archery-target.png"));

    public AccuracyGlyph(int amount) {
        super(ID, amount, ICON, KeywordManager.ACCURACY_GLYPH);
    }

    @Override
    public float modifyDamageFinal(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage * (1 + 0.5f * amount);
    }

    @Override
    public void extraEffect(AbstractCard card, AbstractCreature target, UseCardAction action) {}

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AccuracyGlyph(amount);
    }
}
