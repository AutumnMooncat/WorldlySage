package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractAbilityCard;
import WorldlySage.util.Wiz;
import WorldlySage.vfx.ColoredThrowDaggerEffect;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static WorldlySage.MainModfile.makeID;

public class MudBall extends AbstractAbilityCard {
    public final static String ID = makeID(MudBall.class.getSimpleName());

    public MudBall() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            Wiz.atb(new VFXAction(new ColoredThrowDaggerEffect(m.hb.cX, m.hb.cY, Color.BROWN)));
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}