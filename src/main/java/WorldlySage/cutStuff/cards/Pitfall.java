package WorldlySage.cutStuff.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.blue.AutoShields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Pitfall extends AbstractEasyCard {
    public final static String ID = makeID(Pitfall.class.getSimpleName());
    private boolean justTriggered;

    public Pitfall() {
        super(ID, -2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.NONE);
        baseDamage = damage = 9;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (justTriggered || (purgeOnUse && isInAutoplay)) {
            justTriggered = false;
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return justTriggered || (purgeOnUse && isInAutoplay);
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        justTriggered = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return AutoShields.ID;
    }
}