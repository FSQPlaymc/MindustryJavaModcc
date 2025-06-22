package content;

import mindustry.world.blocks.environment.OreBlock;

public class GG_Block {
    public static OreBlock oreZeta;
    public static void Ore(){
        oreZeta=new OreBlock(GGItems.itemZeta){{
            oreDefault = true;
            oreThreshold = 0.31f;
            oreScale = 10.47619f;
        }};
    }
}
