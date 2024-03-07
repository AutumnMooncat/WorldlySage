package WorldlySage.cards;

import WorldlySage.cardmods.GrowthMod;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Fertilizer extends AbstractEasyCard {
    public final static String ID = makeID(Fertilizer.class.getSimpleName());

    public Fertilizer() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(Wiz.getGrowthAmount(this)));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new GrowthMod(1));
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}