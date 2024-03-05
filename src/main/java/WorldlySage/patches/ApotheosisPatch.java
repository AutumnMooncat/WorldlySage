package WorldlySage.patches;

import WorldlySage.ui.PlantedCardManager;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import javassist.CtBehavior;

public class ApotheosisPatch {
    @SpirePatch2(clz = ApotheosisAction.class, method = "update")
    public static class WorkOnPlantedCards {
        @SpireInsertPatch(locator = Locator.class)
        public static void plz(ApotheosisAction __instance) {
            ReflectionHacks.privateMethod(ApotheosisAction.class, "upgradeAllCardsInGroup", CardGroup.class).invoke(__instance, PlantedCardManager.cards);
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(ApotheosisAction.class, "upgradeAllCardsInGroup");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }
}
