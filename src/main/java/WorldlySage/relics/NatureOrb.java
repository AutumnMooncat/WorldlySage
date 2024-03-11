package WorldlySage.relics;

import WorldlySage.TheWorldlySage;
import WorldlySage.actions.ApplyGrowthAction;
import WorldlySage.actions.PlantCardsAction;
import WorldlySage.cards.Sapling;
import WorldlySage.patches.CustomTags;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static WorldlySage.MainModfile.makeID;

public class NatureOrb extends AbstractEasyRelic {
    public static final String ID = makeID(NatureOrb.class.getSimpleName());
    HashMap<String, Integer> stats = new HashMap<>();
    private final String STAT = DESCRIPTIONS[1];
    private final String PER_TURN = DESCRIPTIONS[2];
    private final String PER_COMBAT = DESCRIPTIONS[3];

    public NatureOrb() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheWorldlySage.Enums.SAGELY_MALIBU_COLOR);
        resetStats();
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0 ; i < 3 ; i++) {
            AbstractCard card = new Sapling();
            card.tags.add(CustomTags.SAGE_RELIC_TRACKER);
            ApplyGrowthAction.applyGrowth(card, 2);
            group.addToTop(card);
        }
        addToBot(new PlantCardsAction(group, group.size()));
    }

/*    @Override
    public void atTurnStartPostDraw() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0 ; i < 3 ; i++) {
            AbstractCard card = new Sapling();
            card.tags.add(CustomTags.SAGE_RELIC_TRACKER);
            ApplyGrowthAction.applyGrowth(card, 2);
            group.addToTop(card);
        }
        addToBot(new PlantCardsAction(group, group.size()));
    }*/

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.hasTag(CustomTags.SAGE_RELIC_TRACKER) && c.block > 0) {
            incrementStat(c.block);
        }
    }

    @Override //Should replace default relic.
    public void obtain() {
        //Grab the player
        AbstractPlayer p = AbstractDungeon.player;
        //If we have the starter relic...
        if (p.hasRelic(WaterOrb.ID)) {
            //Grab its data for relic stats if you want to carry the stats over to the boss relic
            WaterOrb mb = (WaterOrb) p.getRelic(WaterOrb.ID);
            stats.put(STAT, mb.getStat());
            //Find it...
            for (int i = 0; i < p.relics.size(); ++i) {
                if (p.relics.get(i).relicId.equals(WaterOrb.ID)) {
                    //Replace it
                    instantObtain(p, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    //Only spawn if we have the starter relic
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(WaterOrb.ID);
    }

    public void incrementStat(int amount) {
        stats.put(STAT, stats.get(STAT) + amount);
    }

    public String getStatsDescription() {
        return STAT + stats.get(STAT);
    }

    public String getExtendedStatsDescription(int totalCombats, int totalTurns) {
        // You would just return getStatsDescription() if you don't want to display per-combat and per-turn stats
        StringBuilder builder = new StringBuilder();
        builder.append(getStatsDescription());
        float stat = (float)stats.get(STAT);
        // Relic Stats truncates these extended stats to 3 decimal places, so we do the same
        DecimalFormat perTurnFormat = new DecimalFormat("#.###");
        builder.append(PER_TURN);
        builder.append(perTurnFormat.format(stat / Math.max(totalTurns, 1)));
        builder.append(PER_COMBAT);
        builder.append(perTurnFormat.format(stat / Math.max(totalCombats, 1)));
        return builder.toString();
    }

    public void resetStats() {
        stats.put(STAT, 0);
    }

    public JsonElement onSaveStats() {
        // An array makes more sense if you want to store more than one stat
        Gson gson = new Gson();
        ArrayList<Integer> statsToSave = new ArrayList<>();
        statsToSave.add(stats.get(STAT));
        return gson.toJsonTree(statsToSave);
    }

    public void onLoadStats(JsonElement jsonElement) {
        if (jsonElement != null) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            stats.put(STAT, jsonArray.get(0).getAsInt());
        } else {
            resetStats();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        // Relic Stats will always query the stats from the instance passed to BaseMod.addRelic()
        // Therefore, we make sure all copies share the same stats by copying the HashMap.
        NatureOrb newRelic = new NatureOrb();
        newRelic.stats = this.stats;
        return newRelic;
    }
}
