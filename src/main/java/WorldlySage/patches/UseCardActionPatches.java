package WorldlySage.patches;

import WorldlySage.cards.Wormhole;
import WorldlySage.ui.PlantedCardManager;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

public class UseCardActionPatches {
    @SpirePatch2(clz = UseCardAction.class, method = SpirePatch.CLASS)
    public static class PlantField {
        public static SpireField<Boolean> plantNextCard = new SpireField<>(() -> false);
    }

    @SpirePatch2(clz = UseCardAction.class, method = "update")
    public static class FixUCAForWormhole {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<?> plz(UseCardAction __instance, AbstractCard ___targetCard, float ___duration) {
            if (___targetCard instanceof Wormhole) {
                Wormhole card = (Wormhole) ___targetCard;
                if (!card.success) {
                    return SpireReturn.Continue();
                }
                card.success = false;
                //__instance.isDone = true;

                ReflectionHacks.setPrivate(__instance, AbstractGameAction.class, "duration", ___duration - Gdx.graphics.getDeltaTime());

                AbstractDungeon.player.cardInUse = null;
                ___targetCard.exhaustOnUseOnce = false;
                ___targetCard.dontTriggerOnUseCard = false;
                AbstractDungeon.actionManager.addToBottom(new HandCheckAction());

                return SpireReturn.Return(null);
            }

            return SpireReturn.Continue();
        }

        @SpireInsertPatch(locator = Locator2.class)
        public static SpireReturn<?> plz2(UseCardAction __instance, AbstractCard ___targetCard, float ___duration) {
            if (PlantField.plantNextCard.get(__instance)) {
                PlantedCardManager.addCard(___targetCard);

                ReflectionHacks.setPrivate(__instance, AbstractGameAction.class, "duration", ___duration - Gdx.graphics.getDeltaTime());

                AbstractDungeon.player.cardInUse = null;
                ___targetCard.exhaustOnUseOnce = false;
                ___targetCard.dontTriggerOnUseCard = false;
                AbstractDungeon.actionManager.addToBottom(new HandCheckAction());

                return SpireReturn.Return();
            }

            return SpireReturn.Continue();
        }


        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "purgeOnUse");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

        private static class Locator2 extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(UseCardAction.class, "reboundCard");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
