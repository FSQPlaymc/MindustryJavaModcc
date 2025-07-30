package content.GG_Block;

import api.factory;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.Liquid;
import mindustry.type.LiquidStack;

import static mindustry.type.ItemStack.with;

public class GG_factory {
    public static factory SmallGlassKiln;

    public static void factory(){
        SmallGlassKiln=new factory("smallkiln") {{
size=2;
            requirements(Category.crafting, with(Items.silicon,16,Items.beryllium,36,Items.lead,17,Items.graphite,24));
            health = 320;
            craftTime = 60f;
            itemCapacity = 60;//最大物品容量
            hasPower = hasItems = true;
            consumePower(1.8f);
            addInput(Items.sand,1,Items.lead,1);
            outputItem=new ItemStack(Items.metaglass,1);
            outputLiquid=new LiquidStack(Liquids.water,0.05f);
        }};



    }
}
/* 1. scrap -> 碎片
 2. copper -> 铜
 3. lead -> 铅
 4. graphite -> 石墨
 5. coal -> 煤
 6. titanium -> 钛
 7. thorium -> 钍
 8. silicon -> 硅
 9. plastanium -> 塑钢
10. phaseFabric -> 相织物质
11. surgeAlloy -> 冲击合金
12. sporePod -> 孢子囊
13. sand -> 沙
14. blastCompound -> 爆炸化合物
15. pyratite -> 燃烧混合物
16. metaglass -> 金属玻璃
17. beryllium -> 铍（注意：原版没有，但mod常用）
18. tungsten -> 钨（原版没有，但mod常用）
19. oxide -> 氧化物（原版没有，但mod常用）
20. carbide -> 碳化物（原版没有，但mod常用）
21. fissileMatter -> 裂变物质（原版没有，但mod常用）
22. dormantCyst -> 休眠孢囊（原版没有，但根据意思翻译）*/