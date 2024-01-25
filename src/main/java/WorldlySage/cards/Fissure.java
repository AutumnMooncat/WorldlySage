package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Fissure extends AbstractEasyCard {
    public final static String ID = makeID(Fissure.class.getSimpleName());

    public Fissure() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 15;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        Wiz.forAllMonstersLiving(mon -> addToBot(new JudgementAction(mon, magicNumber)));
    }

    @Override
    public void upp() {
        //upgradeDamage(2);
        upgradeMagicNumber(5);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}