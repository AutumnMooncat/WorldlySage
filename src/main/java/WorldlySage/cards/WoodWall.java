package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphAction;
import WorldlySage.actions.DoAction;
import WorldlySage.cardmods.AbstractGlyph;
import WorldlySage.cardmods.ShieldGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.cards.interfaces.KeepsGlyphsCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class WoodWall extends AbstractEasyCard implements KeepsGlyphsCard {
    public final static String ID = makeID(WoodWall.class.getSimpleName());

    public WoodWall() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        CardModifierManager.addModifier(this, new ShieldGlyph(2));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoAction(() -> addToBot(new ApplyGlyphAction(this, new ShieldGlyph(1)))));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new ShieldGlyph(1));
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }

    @Override
    public boolean shouldKeep(AbstractGlyph glyph) {
        return glyph instanceof ShieldGlyph;
    }
}