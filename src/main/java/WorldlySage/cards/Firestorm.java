package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.patches.CardCounterPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Firestorm extends AbstractEasyCard {
    public final static String ID = makeID(Firestorm.class.getSimpleName());

    public Firestorm() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0 ; i < CardCounterPatches.cardsExhaustedThisTurn.size() ; i++) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        }
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        super.applyPowers();
        int count = CardCounterPatches.cardsExhaustedThisTurn.size();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}