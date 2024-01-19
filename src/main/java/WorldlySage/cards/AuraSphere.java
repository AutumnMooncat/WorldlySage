package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphAction;
import WorldlySage.actions.DoAction;
import WorldlySage.actions.ThrowObjectAction;
import WorldlySage.cardmods.AccuracyGlyph;
import WorldlySage.cards.abstracts.AbstractAbilityCard;
import WorldlySage.util.TextureScaler;
import WorldlySage.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class AuraSphere extends AbstractAbilityCard {
    public final static String ID = makeID(AuraSphere.class.getSimpleName());
    private static final Texture EFFECT = TextureScaler.rescale(ImageMaster.vfxAtlas.findRegion("env/dustCloud"), 64, 64);

    public AuraSphere() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            Wiz.atb(new ThrowObjectAction(EFFECT, 1.0f, m.hb, Color.WHITE, false));
        }
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new DoAction(() -> addToBot(new ApplyGlyphAction(this, new AccuracyGlyph(1)))));
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