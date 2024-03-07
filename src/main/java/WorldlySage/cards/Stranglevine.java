package WorldlySage.cards;

import WorldlySage.cardmods.GrowthMod;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;

import static WorldlySage.MainModfile.makeID;

public class Stranglevine extends AbstractEasyCard {
    public final static String ID = makeID(Stranglevine.class.getSimpleName());

    public Stranglevine() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 5;
        CardModifierManager.addModifier(this, new GrowthMod(2));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        Wiz.applyToEnemy(m, new ChokePower(m, Wiz.getGrowthAmount(this)));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new GrowthMod(1));
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}