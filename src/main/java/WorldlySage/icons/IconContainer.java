package WorldlySage.icons;

import WorldlySage.MainModfile;
import WorldlySage.cardmods.*;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;

public class IconContainer {
    public static void register() {
        makeIcon("Growth", GrowthMod.modIcon);
        makeIcon("Energy", EnergyGlyph.ICON);
        makeIcon("Draw", DrawGlyph.ICON);
        makeIcon("Pierce", PiercingGlyph.ICON);
        makeIcon("Shield", ShieldGlyph.ICON);
    }
    
    private static void makeIcon(String ID, Texture icon) {
        CustomIconHelper.addCustomIcon(new AbstractCustomIcon(MainModfile.makeID(ID), icon) {});
    }
}
