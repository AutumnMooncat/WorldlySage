package WorldlySage.cards;

import WorldlySage.cardmods.GrowthMod;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.cards.interfaces.OnGrowthCard;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import static WorldlySage.MainModfile.makeID;

public class LivingFlame extends AbstractEasyCard implements OnGrowthCard {
    public final static String ID = makeID(LivingFlame.class.getSimpleName());

    public LivingFlame() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        CardModifierManager.addModifier(this, new GrowthMod(5));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new SFXAction("ATTACK_FIRE", 0.3f));
        Wiz.atb(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.FIREBRICK, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        Wiz.atb(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(Wiz.getGrowthAmount(this), true), DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new GrowthMod(3));
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }

    @Override
    public int onGrow(int amount) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard card = makeStatEquivalentCopy();
                card.purgeOnUse = true;
                card.current_x = current_x;
                card.current_y = current_y;
                addToTop(new NewQueueCardAction(card, true, true, true));
                this.isDone = true;
            }
        });
        return amount;
    }
}