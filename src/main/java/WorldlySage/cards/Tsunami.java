package WorldlySage.cards;

import WorldlySage.actions.FlowAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Tsunami extends AbstractEasyCard {
    public final static String ID = makeID(Tsunami.class.getSimpleName());

    public Tsunami() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 17;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        Wiz.atb(new FlowAction(cards -> {
            if (!cards.isEmpty()) {
                Wiz.att(new GainEnergyAction(cards.size()*magicNumber));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(6);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}