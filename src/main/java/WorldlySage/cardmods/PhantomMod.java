package WorldlySage.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static WorldlySage.MainModfile.makeID;

public class PhantomMod extends AbstractCardModifier {
    public static String ID = makeID(PhantomMod.class.getSimpleName());
    public static String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0] + rawDescription;
    }

    public void onInitialApplication(AbstractCard card) {
        card.isEthereal = true;
        if (card.type != AbstractCard.CardType.POWER) {
            card.exhaust = true;
        }
    }

    public AbstractCardModifier makeCopy() {
        return new PhantomMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
