package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
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
    public Runnable onSuccess;

    public Wormhole() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int handIndex = p.hand.group.indexOf(this);
        addToBot(new MultiGroupSelectAction(cardStrings.EXTENDED_DESCRIPTION[0], (cards, groups) -> {
            for (AbstractCard c : cards) {
                CardGroup group = groups.get(c);
                int index = group.group.indexOf(c);

                //Prepare the runnable for the UCA Patch
                onSuccess = () -> {
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
                    }
                };

                //Unfade the original card in case it came from the Exhaust pile
                c.unfadeOut();

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

                //Refresh hand logic given we manually added to hand
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();

                //Tell UCA to run the runnable if we are upgraded, else let it go through normal UCA logic
                //This means spoon can make the non-upgraded version discard instead of moving like the upgraded version
                success = upgraded;
            }
        }, 1, CardGroup.CardGroupType.DRAW_PILE, CardGroup.CardGroupType.DISCARD_PILE, CardGroup.CardGroupType.EXHAUST_PILE));
    }

    @Override
    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}