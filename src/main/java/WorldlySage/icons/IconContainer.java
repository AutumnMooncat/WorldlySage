package WorldlySage.icons;

import WorldlySage.MainModfile;
import WorldlySage.cardmods.DrawGlyph;
import WorldlySage.cardmods.EnergyGlyph;
import WorldlySage.cardmods.GrowthMod;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

public class IconContainer {
    public static class Growth extends AbstractCustomIcon {
        static AbstractCustomIcon singleton;

        public Growth() {
            super(MainModfile.makeID("Growth"), GrowthMod.modIcon);
        }

        public static AbstractCustomIcon get() {
            if (singleton == null) {
                singleton = new Growth();
            }
            return singleton;
        }
    }

    public static class Energy extends AbstractCustomIcon {
        static AbstractCustomIcon singleton;

        public Energy() {
            super(MainModfile.makeID("Energy"), EnergyGlyph.modIcon);
        }

        public static AbstractCustomIcon get() {
            if (singleton == null) {
                singleton = new Energy();
            }
            return singleton;
        }
    }

    public static class Draw extends AbstractCustomIcon {
        static AbstractCustomIcon singleton;

        public Draw() {
            super(MainModfile.makeID("Draw"), DrawGlyph.modIcon);
        }

        public static AbstractCustomIcon get() {
            if (singleton == null) {
                singleton = new Draw();
            }
            return singleton;
        }
    }
}
