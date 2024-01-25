package WorldlySage.cards;

import WorldlySage.cardmods.AbstractGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.cards.interfaces.KeepsGlyphsCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

import static WorldlySage.MainModfile.makeID;

public class Crystallize extends AbstractEasyCard implements KeepsGlyphsCard {
    public final static String ID = makeID(Crystallize.class.getSimpleName());

    public Crystallize() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 6;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BlizzardEffect(damage, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY, true);
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY, true);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }

    @Override
    public boolean shouldKeep(AbstractGlyph glyph) {
        return true;
    }
}