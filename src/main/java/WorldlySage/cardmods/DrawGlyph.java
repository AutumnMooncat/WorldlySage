package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import WorldlySage.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static WorldlySage.MainModfile.makeID;

public class DrawGlyph extends AbstractGlyph {
    public static String ID = makeID(DrawGlyph.class.getSimpleName());
    public static Texture ICON = TexLoader.getTexture(MainModfile.makeImagePath("icons/card-draw.png"));

    public DrawGlyph(int amount) {
        super(ID, amount, ICON, KeywordManager.DRAW_GLYPH);
    }

    @Override
    public void extraEffect(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new DrawCardAction(amount));
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DrawGlyph(amount);
    }
}
