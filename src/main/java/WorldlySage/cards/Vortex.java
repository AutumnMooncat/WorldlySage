package WorldlySage.cards;

import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Vortex extends AbstractEasyCard {
    public final static String ID = makeID(Vortex.class.getSimpleName());

    public Vortex() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterDrawPileToHandAction(magicNumber));
    }

    @Override
    public boolean freeToPlay() {
        return super.freeToPlay() || Wiz.hasGlyph(this);
    }

    @Override
    public void upp() {
        //upgradeMagicNumber(1);
        CardModifierManager.addModifier(this, new EnergyGlyph(1));
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}