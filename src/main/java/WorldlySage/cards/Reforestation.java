package WorldlySage.cards;

import WorldlySage.actions.EasyXCostAction;
import WorldlySage.actions.PlantCardsAction;
import WorldlySage.cards.abstracts.AbstractEasyCard;
import WorldlySage.powers.ReforestationPower;
import WorldlySage.util.Wiz;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static WorldlySage.MainModfile.makeID;

public class Reforestation extends AbstractEasyCard {
    public final static String ID = makeID(Reforestation.class.getSimpleName());

    public Reforestation() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
        cardsToPreview = new Sapling();
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (base, args) -> {
            int effect = base;
            for (int i : args) {
                effect += i;
            }
            if (effect > 0) {
                CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (int i = 0 ; i < 2 ; i++) {
                    group.addToTop(new Sapling());
                }
                Wiz.applyToSelfTop(new ReforestationPower(p, effect));
                addToTop(new PlantCardsAction(group, group.size()));
            }
            return true;
        }));

    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return BladeDance.ID;
    }
}