package WorldlySage.ui;

import WorldlySage.actions.ApplyGrowthAction;
import WorldlySage.cards.interfaces.OnPlantCard;
import WorldlySage.patches.CardCounterPatches;
import WorldlySage.powers.interfaces.OnPlantPower;
import WorldlySage.util.Wiz;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
import com.megacrit.cardcrawl.vfx.BobEffect;
import javassist.CtBehavior;

public class PlantedCardManager {
    public static final float Y_OFFSET = 70f * Settings.scale;
    public static final float X_OFFSET = 100f * Settings.scale;
    public static final CardGroup cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private static final BobEffect bob = new BobEffect(3.0f * Settings.scale, 3.0f);
    public static AbstractCard hovered;

    public static void render(SpriteBatch sb) {
        for (AbstractCard card : cards.group) {
            if (card != hovered) {
                card.render(sb);
            }
        }
        if (hovered != null) {
            hovered.render(sb);
            TipHelper.renderTipForCard(hovered, sb, hovered.keywords);
        }
    }

    public static void update() {
        bob.update();
        int i = 0;
        int j = 0;
        hovered = null;
        boolean toRemove = false;
        for (AbstractCard card : cards.group) {
            card.target_y = Wiz.adp().hb.cY + Wiz.adp().hb.height/2f + Y_OFFSET*(j+1) + bob.y;
            card.target_x = Wiz.adp().hb.cX + X_OFFSET * Math.min(9, (cards.size()-1-10*j)) / 2f - X_OFFSET * i;
            card.targetAngle = 0f;
            card.update();
            card.hb.update();
            if (card.hb.hovered && hovered == null) {
                card.targetDrawScale = 0.75f;
                hovered = card;
                if (!AbstractDungeon.isScreenUp && HitboxRightClick.rightClicked.get(card.hb) && !AbstractDungeon.actionManager.turnHasEnded) {
                    toRemove = true;
                }
            } else {
                card.targetDrawScale = 0.2f;
            }
            card.applyPowers();
            i++;
            if (i == 10) {
                i = 0;
                j++;
            }
        }
        if (toRemove) {
            if (Wiz.adp().hand.size() < BaseMod.MAX_HAND_SIZE) {
                cards.group.remove(hovered);
                AbstractDungeon.player.hand.addToTop(hovered);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
            } else {
                Wiz.adp().createHandIsFullDialog();
            }
        }
    }


    public static void addCard(AbstractCard card) {
        addCard(card, true, false);
    }

    public static void addCard(AbstractCard card, boolean playSFX) {
        addCard(card, playSFX, false);
    }

    public static void addCard(AbstractCard card, boolean playSFX, boolean isEndTurn) {
        card.targetAngle = 0f;
        card.beginGlowing();
        cards.addToTop(card);
        if (card instanceof OnPlantCard) {
            ((OnPlantCard) card).onPlant();
        }
        for (AbstractPower p : Wiz.adp().powers) {
            if (p instanceof OnPlantPower) {
                ((OnPlantPower) p).onPlant(card, isEndTurn);
            }
        }
        if (playSFX) {
            CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1F);
        }
        CardCounterPatches.cardsProjectedThisTurn++;
        CardCounterPatches.cardsProjectedThisCombat++;
    }

    @SpirePatch2(clz = AbstractCard.class, method = SpirePatch.CLASS)
    public static class ProjectedCardFields {
        public static SpireField<Boolean> projectedField = new SpireField<>(() -> false);
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "applyStartOfTurnCards")
    public static class GrowCards {
        @SpirePrefixPatch
        public static void growCards() {
            for (AbstractCard c : cards.group) {
                ApplyGrowthAction.applyGrowth(c, 1);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "render")
    public static class RenderPanel {
        @SpireInsertPatch(locator = Locator.class)
        public static void render(OverlayMenu __instance, SpriteBatch sb) {
            PlantedCardManager.render(sb);
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(ExhaustPanel.class, "render");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "combatUpdate")
    public static class UpdatePile {
        @SpirePostfixPatch
        public static void update(AbstractPlayer __instance) {
            PlantedCardManager.update();
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "preBattlePrep")
    @SpirePatch2(clz = AbstractPlayer.class, method = "onVictory")
    public static class EmptyCards {
        @SpirePostfixPatch
        public static void yeet() {
            cards.clear();
        }
    }

    @SpirePatch2(clz = PerfectedStrike.class, method = "countCards")
    public static class CountProjectedCardsPlz {
        @SpirePostfixPatch
        public static int getCount(int __result) {
            int projectedStrikes = (int)(PlantedCardManager.cards.group.stream().filter(c -> c.hasTag(AbstractCard.CardTags.STRIKE)).count());
            return __result + projectedStrikes;
        }
    }
}
