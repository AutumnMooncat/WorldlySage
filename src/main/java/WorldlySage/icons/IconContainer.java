package WorldlySage.icons;

import WorldlySage.MainModfile;
import WorldlySage.cardmods.GrowthMod;
import WorldlySage.util.TexLoader;
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
}
