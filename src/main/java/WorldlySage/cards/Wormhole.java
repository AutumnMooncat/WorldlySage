package WorldlySage.cards;

import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MultiGroupSelectAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;

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
}