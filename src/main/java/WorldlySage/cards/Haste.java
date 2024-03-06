package WorldlySage.cards;

import WorldlySage.actions.DoAction;
import WorldlySage.cardmods.PhantomMod;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Haste extends AbstractEasyCard {
    public final static String ID = makeID(Haste.class.getSimpleName());
    private int lastSize;

    public Haste() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        exhaust = true;
        rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoAction(() -> {
            AbstractCard card = null;
            //Start at the second last card, as the last card is always this
            for (int i = Wiz.cardsPlayedThisCombat().size() - 2 ; i >= 0 ; i--) {
                if (!(Wiz.cardsPlayedThisCombat().get(i) instanceof Haste)) {
                    card = Wiz.cardsPlayedThisCombat().get(i);
                    break;
                }
            }
            if (card != null) {
                card = card.makeStatEquivalentCopy();
                CardModifierManager.addModifier(card, new PhantomMod());
                addToTop(new MakeTempCardInHandAction(card));
            }
        }));
        rawDescription = cardStrings.DESCRIPTION + (upgraded ? "" : cardStrings.EXTENDED_DESCRIPTION[2]);
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (Wiz.cardsPlayedThisCombat().size() != lastSize) {
            lastSize = Wiz.cardsPlayedThisCombat().size();
            AbstractCard card = null;
            for (int i = Wiz.cardsPlayedThisCombat().size() - 1 ; i >= 0 ; i--) {
                if (!(Wiz.cardsPlayedThisCombat().get(i) instanceof Haste)) {
                    card = Wiz.cardsPlayedThisCombat().get(i);
                    break;
                }
            }
            if (card != null) {
                rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + CardModifierManager.onRenderTitle(card, card.name) + cardStrings.EXTENDED_DESCRIPTION[1] + (upgraded ? "" : cardStrings.EXTENDED_DESCRIPTION[2]);
                cardsToPreview = card.makeStatEquivalentCopy();
                initializeDescription();
            }
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        super.triggerOnOtherCardPlayed(c);
    }

    @Override
    public void upp() {
        //upgradeBaseCost(0);
        exhaust = false;
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}