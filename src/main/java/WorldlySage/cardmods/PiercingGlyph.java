package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.damageMods.TempPiercingDamage;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static WorldlySage.MainModfile.makeID;


public class PiercingGlyph extends AbstractCardModifier {
    public static String ID = makeID(PiercingGlyph.class.getSimpleName());
    public static Texture modIcon = TexLoader.getTexture(MainModfile.makeImagePath("icons/drill.png"));
    private static final ArrayList<TooltipInfo> tips = new ArrayList<>(Collections.singletonList(new TooltipInfo(BaseMod.getKeywordTitle(KeywordManager.PIERCE_GLYPH), BaseMod.getKeywordDescription(KeywordManager.PIERCE_GLYPH))));
    private final TempPiercingDamage tpd = new TempPiercingDamage();
    public int amount;

    public PiercingGlyph(int amount) {
        this.amount = amount;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        amount--;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        DamageModifierManager.addModifier(card, tpd);
    }

    @Override
    public void onRemove(AbstractCard card) {
        DamageModifierManager.removeModifier(card, tpd);
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
            PiercingGlyph glyph = (PiercingGlyph) CardModifierManager.getModifiers(card, ID).get(0);
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
        return new PiercingGlyph(amount);
    }
}
