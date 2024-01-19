package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphAction;
import WorldlySage.actions.DamageFollowupAction;
import WorldlySage.actions.DoAction;
import WorldlySage.actions.ThrowObjectAction;
import WorldlySage.cardmods.PiercingGlyph;
import WorldlySage.cards.abstracts.AbstractAbilityCard;
import WorldlySage.util.TextureScaler;
import WorldlySage.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.WallopEffect;

import static WorldlySage.MainModfile.makeID;

public class Star extends AbstractAbilityCard {
    public final static String ID = makeID(Star.class.getSimpleName());
    private static final Texture STAR_TEX = TextureScaler.rescale(ImageMaster.TINY_STAR, 64, 64);

    public Star() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        CardModifierManager.addModifier(this, new PiercingGlyph(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            Wiz.atb(new ThrowObjectAction(STAR_TEX, 1.0f, m.hb, Color.GOLD, false));
            Wiz.atb(new DamageFollowupAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true, mon -> addToTop(new VFXAction(new WallopEffect(mon.lastDamageTaken, mon.hb.cX, mon.hb.cY)))));
            addToBot(new DoAction(() -> addToBot(new ApplyGlyphAction(this, new PiercingGlyph(1)))));
        }
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