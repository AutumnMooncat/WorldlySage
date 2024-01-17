package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import WorldlySage.util.Wiz;
import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;
import java.util.Collections;

import static WorldlySage.MainModfile.makeID;

public class ShieldGlyph extends AbstractGlyph {
    public static String ID = makeID(ShieldGlyph.class.getSimpleName());
    public static Texture ICON = TexLoader.getTexture(MainModfile.makeImagePath("icons/bordered-shield.png"));
    private static final ArrayList<TooltipInfo> TIPS = new ArrayList<>(Collections.singletonList(new TooltipInfo(BaseMod.getKeywordTitle(KeywordManager.SHIELD_GLYPH), BaseMod.getKeywordDescription(KeywordManager.SHIELD_GLYPH))));

    public ShieldGlyph(int amount) {
        super(ID, amount, ICON, TIPS);
    }

    @Override
    public void extraEffect(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new GainBlockAction(Wiz.adp(), 3));
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ShieldGlyph(amount);
    }
}
