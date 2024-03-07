package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class CrystalShards extends AbstractEasyCard {
    public final static String ID = makeID(CrystalShards.class.getSimpleName());

    public CrystalShards() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 3;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0 ; i < magicNumber ; i++) {
            dmg(m, i%3 == 0 ? AbstractGameAction.AttackEffect.SLASH_VERTICAL : i%3 == 1 ? AbstractGameAction.AttackEffect.SLASH_DIAGONAL : AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}