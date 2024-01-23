package WorldlySage.cards;

import WorldlySage.cardmods.GrowthMod;
import WorldlySage.cards.abstracts.AbstractAbilityCard;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Geyser extends AbstractAbilityCard {
    public final static String ID = makeID(Geyser.class.getSimpleName());

    public Geyser() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        CardModifierManager.addModifier(this, new GrowthMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new DrawCardAction(Wiz.getGrowthAmount(this)));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new GrowthMod(1));
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}