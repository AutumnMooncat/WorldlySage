package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import WorldlySage.util.Wiz;
import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static WorldlySage.MainModfile.makeID;


public class ShieldGlyph extends AbstractCardModifier {
    public static String ID = makeID(ShieldGlyph.class.getSimpleName());
    public static Texture modIcon = TexLoader.getTexture(MainModfile.makeImagePath("icons/bordered-shield.png"));
    private static final ArrayList<TooltipInfo> tips = new ArrayList<>(Collections.singletonList(new TooltipInfo(BaseMod.getKeywordTitle(KeywordManager.SHIELD_GLYPH), BaseMod.getKeywordDescription(KeywordManager.SHIELD_GLYPH))));
    public int amount;

    public ShieldGlyph(int amount) {
        this.amount = amount;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        amount--;
        Wiz.atb(new GainBlockAction(Wiz.adp(), 3));
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return amount == 0;
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        return tips;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        if (SingleCardViewPopup.isViewingUpgrade && !card.upgraded) {
            return;
        }
        if (amount == 1) {
            ExtraIcons.icon(modIcon).textOffsetX(3).drawColor(new Color(1, 1, 1, card.transparency)).render(card);
        } else {
            ExtraIcons.icon(modIcon).text(String.valueOf(amount)).textOffsetX(3).drawColor(new Color(1, 1, 1, card.transparency)).render(card);
        }
    }

    @Override
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if (amount == 1) {
            ExtraIcons.icon(modIcon).textOffsetX(6).drawColor(new Color(1, 1, 1, card.transparency)).render(card);
        } else {
            ExtraIcons.icon(modIcon).text(String.valueOf(amount)).textOffsetX(6).drawColor(new Color(1, 1, 1, card.transparency)).render(card);
        }
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, ID)) {
            ShieldGlyph glyph = (ShieldGlyph) CardModifierManager.getModifiers(card, ID).get(0);
            glyph.amount += this.amount;
            return false;
        }
        return true;
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
