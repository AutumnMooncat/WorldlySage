package WorldlySage.cards;

import WorldlySage.actions.PredicateDrawPileToHandAction;
import WorldlySage.cards.abstracts.AbstractAbilityCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class WaterBlade extends AbstractAbilityCard {
    public final static String ID = makeID(WaterBlade.class.getSimpleName());

    public WaterBlade() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 5;
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.atb(new PredicateDrawPileToHandAction(magicNumber, c -> c.costForTurn == 0 || c.freeToPlayOnce));
    }

    public void triggerOnGlowCheck() {
        if (Wiz.adp().drawPile.group.stream().noneMatch(c -> c.costForTurn == 0 || c.freeToPlayOnce)) {
            this.glowColor = Settings.RED_TEXT_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
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