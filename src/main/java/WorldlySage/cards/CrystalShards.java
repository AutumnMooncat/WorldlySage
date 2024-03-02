package WorldlySage.cards;

import WorldlySage.actions.ApplyGlyphToAllCardsAction;
import WorldlySage.cardmods.PiercingGlyph;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class CrystalShards extends AbstractEasyCard {
    public final static String ID = makeID(CrystalShards.class.getSimpleName());

    public CrystalShards() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new ApplyGlyphToAllCardsAction(new PiercingGlyph(1), c -> Wiz.adp().hand.contains(c) && c.type == CardType.ATTACK));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }
}