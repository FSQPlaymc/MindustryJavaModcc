package content;


import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.production.GenericCrafter;

public class GG_Block {

    public static GenericCrafter chuangZaolu;
    public static OreBlock oreZeta;
    public static void Ore(){

        oreZeta=new OreBlock("zeta"){{
            itemDrop = GGItems.zeta; // 掉落物（必须）
            oreDefault = true;
            oreThreshold = 0.71f;
            oreScale = 10.47619f;
            variants = 3;//三种贴图在blocks/environment中
            //fullIcon=Core.atlas.find("custom-ores/ore_zeta");
        }};
        chuangZaolu = new GenericCrafter("chuangzaolu"){{//贴图在
        requirements(Category.crafting, ItemStack.with(GGItems.zeta,30,
                Items.copper,50,
                Items.surgeAlloy,10,
                Items.silicon,40,
                Items.titanium,70,
                Items.plastanium,30
                ));
            craftEffect = Fx.pulverizeMedium;
            outputItem = new ItemStack(GGItems.itemCZzw, 2);//产出物品
            craftTime = 300f;//生产时间
            itemCapacity = 20;//物品数
            size = 4;
            hasItems = true;//消耗物品
            hasLiquids = true;//消耗流体
            hasPower = true;//消耗电力

            consumePower(90.0f);//*100
            consumeItem(GGItems.zeta, 10);
            consumeLiquid(Liquids.oil, 1.9f);

        }};
    }
}
/*重要原则：
当使用 new OreBlock("name") 时，必须在 sprites/blocks/ores/ 下有对应的 ore-name.png 文件。
若使用自定义路径，则必须显式声明 sprite = Core.atlas.find("your/path")。
二者必选其一，否则矿石无法显示贴图。*/