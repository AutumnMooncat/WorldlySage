package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphToAllCardsAction;
import WorldlySage.cardmods.PiercingGlyph;
import WorldlySage.cardmods.ShieldGlyph;
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

public class RockPolish extends AbstractEasyCard {
    public final static String ID = makeID(RockPolish.class.getSimpleName());

    public RockPolish() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ApplyGlyphToAllCardsAction(new ShieldGlyph(1), c -> Wiz.adp().hand.contains(c) && c.type == CardType.SKILL));
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