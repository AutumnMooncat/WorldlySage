package WorldlySage.cards;

import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import WorldlySage.vfx.DirectedParticleEffect;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static WorldlySage.MainModfile.makeID;

public class MudSlide extends AbstractEasyCard {
    public final static String ID = makeID(MudSlide.class.getSimpleName());

    public MudSlide() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                CardCrawlGame.sound.play("APPEAR");
                for (int i = 0 ; i < 10 ; i++) {
                    for (int j = 0 ; j < 20 ; j++) {
                        AbstractDungeon.effectList.add(new DirectedParticleEffect(Color.BROWN.cpy(), p.hb.cX, p.hb.cY, MathUtils.random(50f, 75f) * j, i * j));
                        AbstractDungeon.effectList.add(new DirectedParticleEffect(Color.BROWN.cpy(), p.hb.cX, p.hb.cY, MathUtils.random(50f, 75f) * j, -i * j));
                    }
                }
                this.isDone = true;
            }
        });
        addToBot(new DrawCardAction(magicNumber));
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new VulnerablePower(mon, 1, false)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}