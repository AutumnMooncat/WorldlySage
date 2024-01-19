package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractAbilityCard;
import WorldlySage.powers.StealthRocksPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class StealthRocks extends AbstractAbilityCard {
    public final static String ID = makeID(StealthRocks.class.getSimpleName());

    public StealthRocks() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new DamageFollowupAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY, false, mon -> Wiz.applyToSelfTop(new StealthRocksPower(p, mon.lastDamageTaken))));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.applyToSelf(new StealthRocksPower(p, damage));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}