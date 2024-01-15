package WorldlySage.cardmods;

import WorldlySage.MainModfile;
import WorldlySage.cards.interfaces.GrowthModifierCard;
import WorldlySage.util.KeywordManager;
import WorldlySage.util.TexLoader;
import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static WorldlySage.MainModfile.makeID;


public class GrowthMod extends AbstractCardModifier {
    public static String ID = makeID(GrowthMod.class.getSimpleName());
    public static Texture modIcon = /*TextureScaler.rescale(*/TexLoader.getTexture(MainModfile.makeImagePath("icons/GrowthToken2.png"))/*, 64, 64)*/;
    private static final ArrayList<TooltipInfo> tips = new ArrayList<>(Collections.singletonList(new TooltipInfo(BaseMod.getKeywordTitle(KeywordManager.GROWTH), BaseMod.getKeywordDescription(KeywordManager.GROWTH))));
    public int amount;

    public GrowthMod(int amount) {
        this.amount = amount;
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        if (damage >= 0) {
            damage += amount * (card instanceof GrowthModifierCard ? ((GrowthModifierCard)card).boostPerGrowth() : 1);
        }
        return damage;
    }

    @Override
    public float modifyBaseBlock(float block, AbstractCard card) {
        if (block >= 0) {
            block += amount * (card instanceof GrowthModifierCard ? ((GrowthModifierCard)card).boostPerGrowth() : 1);
        }
        return block;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        ArrayList<AbstractCardModifier> mods = CardModifierManager.getModifiers(card, ID);
        if (!mods.isEmpty()) {
            GrowthMod mod = (GrowthMod) mods.get(0);
            mod.amount += amount;
            return false;
        }
        return true;
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        return tips;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(modIcon).text(String.valueOf(amount)).textOffsetX(3).drawColor(new Color(1, 1, 1, card.transparency)).render(card);
    }

    @Override
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(modIcon).text(String.valueOf(amount)).textOffsetX(6).drawColor(new Color(1, 1, 1, card.transparency)).render(card);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GrowthMod(amount);
    }
}
