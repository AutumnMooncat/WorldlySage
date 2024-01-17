package WorldlySage.icons;

import WorldlySage.MainModfile;
import WorldlySage.cardmods.*;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;

public class IconContainer {
    public static void register() {
        makeIcon("Growth", GrowthMod.modIcon);
        makeIcon("Energy", EnergyGlyph.modIcon);
        makeIcon("Draw", DrawGlyph.modIcon);
        makeIcon("Pierce", PiercingGlyph.modIcon);
        makeIcon("Shield", ShieldGlyph.modIcon);
    }
    
    private static void makeIcon(String ID, Texture icon) {
        CustomIconHelper.addCustomIcon(new AbstractCustomIcon(MainModfile.makeID(ID), icon) {});
    }
}
