package WorldlySage.cards;

import WorldlySage.actions.BetterSelectCardsInHandAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Sigil extends AbstractEasyCard {
    public final static String ID = makeID(Sigil.class.getSimpleName());

    public Sigil() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], false, false, c -> c.type == CardType.ATTACK, l -> {
            for (AbstractCard c : l) {
                c.superFlash();
                addToTop(new ModifyDamageAction(c.uuid, magicNumber));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}