package WorldlySage.powers.interfaces;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface OnPlantPower {
    void onPlant(AbstractCard card, boolean isEndTurn);
}
