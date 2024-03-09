package WorldlySage.cards;

import WorldlySage.actions.BetterSelectCardsInHandAction;
import WorldlySage.cardmods.PhantomMod;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.DualWieldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class TunnelVision extends AbstractEasyCard {
    public final static String ID = makeID(TunnelVision.class.getSimpleName());

    public TunnelVision() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(1, DualWieldAction.TEXT[0], false, false, c -> true, l -> {
            for (AbstractCard c : l) {
                AbstractCard copy = c.makeStatEquivalentCopy();
                CardModifierManager.addModifier(copy, new PhantomMod());
                addToTop(new MakeTempCardInHandAction(copy, p.hand.size()+1));
                addToTop(new DiscardAction(p, p, p.hand.size()+1, true));
            }
        }));
    }

    @Override
    public void upp() {
        //CardModifierManager.addModifier(this, new EnergyGlyph(1));
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}