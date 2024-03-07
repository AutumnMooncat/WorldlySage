package WorldlySage.cards;

import WorldlySage.actions.DamageFollowupAction;
import WorldlySage.actions.ThrowObjectAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.patches.EnterCardGroupPatches;
import WorldlySage.powers.CrushPower;
import WorldlySage.util.TextureScaler;
import WorldlySage.util.Wiz;
import WorldlySage.vfx.ApplyShaderEffect;
import WorldlySage.vfx.LensingEffect;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.WallopEffect;

import static WorldlySage.MainModfile.makeID;

public class BlackHole extends AbstractEasyCard implements EnterCardGroupPatches.OnEnterCardGroupCard {
    public final static String ID = makeID(BlackHole.class.getSimpleName());
    private static final Texture STAR_TEX = TextureScaler.rescale(ImageMaster.TINY_STAR, 1, 1);
    private CardGroup lastGroup;

    public BlackHole() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 25;
        baseMagicNumber = magicNumber = 3;
        //exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            LensingEffect le = new LensingEffect(p.hb.cX, p.hb.cY);
            ApplyShaderEffect ase = new ApplyShaderEffect(le, 1.50f);
            Wiz.atb(new VFXAction(ase));
            Wiz.atb(new ThrowObjectAction(STAR_TEX, 1.0f, m.hb, Color.BLACK, true, (builder, batch) -> le.updatePosition(builder.x, builder.y)));
            Wiz.atb(new VFXAction(new AbstractGameEffect() {
                @Override
                public void update() {
                    le.shrinkSize();
                    this.isDone = ase.isDone;
                }

                @Override
                public void render(SpriteBatch spriteBatch) {}

                @Override
                public void dispose() {}
            }));
            Wiz.atb(new DamageFollowupAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true, mon -> addToTop(new VFXAction(new WallopEffect(mon.lastDamageTaken, mon.hb.cX, mon.hb.cY)))));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(10);
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }

    @Override
    public void onEnter(CardGroup g) {
        if (g != lastGroup) {
            lastGroup = g;
            if (g == Wiz.adp().hand) {
                superFlash();
                Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new CrushPower(mon, magicNumber)));
            }
        }
    }
}