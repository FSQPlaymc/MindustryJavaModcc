package content;


import mindustry.world.blocks.environment.OreBlock;

public class GG_Block {
    public static OreBlock oreZeta;
    public static void Ore(){
        oreZeta=new OreBlock("zeta"){{
            itemDrop = GGItems.zeta; // 掉落物（必须）
            oreDefault = true;
            oreThreshold = 0.71f;
            oreScale = 10.47619f;
            variants = 3;//三种贴图
            //fullIcon=Core.atlas.find("custom-ores/ore_zeta");

        }};
    }
}
/*重要原则：
当使用 new OreBlock("name") 时，必须在 sprites/blocks/ores/ 下有对应的 ore-name.png 文件。
若使用自定义路径，则必须显式声明 sprite = Core.atlas.find("your/path")。
二者必选其一，否则矿石无法显示贴图。*/