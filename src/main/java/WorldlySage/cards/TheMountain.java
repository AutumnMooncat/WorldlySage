package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphAction;
import WorldlySage.cardmods.ShieldGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class TheMountain extends AbstractEasyCard {
    public final static String ID = makeID(TheMountain.class.getSimpleName());

    public TheMountain() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true, true)) {
                    ApplyGlyphAction.applyGlyph(c, new ShieldGlyph(magicNumber));
                }
            }
        });
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}