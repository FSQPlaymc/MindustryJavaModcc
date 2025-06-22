package content;

import arc.Core;
import mindustry.world.blocks.environment.OreBlock;

public class GG_Block {
    public static OreBlock oreZeta;
    public static void Ore(){
        oreZeta=new OreBlock("ore_Zeta"){{
            itemDrop = GGItems.itemZeta; // 掉落物（必须）
            oreDefault = true;
            oreThreshold = 0.71f;
            oreScale = 10.47619f;

        }};
    }
}
