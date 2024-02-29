package WorldlySage.cards;

import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import basemod.ReflectionHacks;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MultiGroupSelectAction;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import javassist.CtBehavior;

import static WorldlySage.MainModfile.makeID;

public class Wormhole extends AbstractEasyCard {
    public final static String ID = makeID(Wormhole.class.getSimpleName());
    public boolean success;

    public Wormhole() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int handIndex = p.hand.group.indexOf(this);
        addToBot(new MultiGroupSelectAction(cardStrings.EXTENDED_DESCRIPTION[0], (cards, groups) -> {
            for (AbstractCard c : cards) {
                CardGroup group = groups.get(c);
                int index = group.group.indexOf(c);
                //Actually add the card to the pile
                group.group.add(index, this);
                //Animate the card cosmetically
                unhover();
                untip();
                stopGlowing();
                if (group == p.drawPile) {
                    shrink();
                    darken(false);
                    AbstractDungeon.getCurrRoom().souls.onToDeck(this, true, true);
                } else if (group == p.discardPile) {
                    shrink();
                    darken(false);
                    AbstractDungeon.getCurrRoom().souls.discard(this, true);
                } else if (group == p.exhaustPile) {
                    AbstractDungeon.effectList.add(new ExhaustCardEffect(this));
                    //Also unfade the original card
                    c.unfadeOut();
                }
                //Move original card to hand
                c.unhover();
                c.untip();
                c.stopGlowing();
                group.group.remove(c);
                c.lighten(true);
                c.setAngle(0.0F);
                c.drawScale = 0.12F;
                c.targetDrawScale = 0.75F;
                if (handIndex != -1) {
                    p.hand.group.add(handIndex, c);
                } else {
                    p.hand.addToTop(c);
                }

                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
                success = true;
            }
        }, 1, CardGroup.CardGroupType.DRAW_PILE, CardGroup.CardGroupType.DISCARD_PILE, CardGroup.CardGroupType.EXHAUST_PILE));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new EnergyGlyph(1));
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }

    @SpirePatch2(clz = UseCardAction.class, method = "update")
    public static class FixUCA {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<?> UseCardActionPatch(UseCardAction __instance, AbstractCard ___targetCard, float ___duration) {
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