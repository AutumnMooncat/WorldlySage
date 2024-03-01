package WorldlySage.patches;

import WorldlySage.cards.Wormhole;
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


        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "purgeOnUse");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
