package content.GG_Block;

import api.factory;
import api.more_factory.ConsumePower;
import arc.graphics.Color;
import content.GGItems;
import content.GG_Liquids;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class GG_factory {
    public static factory SmallGlassKiln;
    public static factory SGfacto;
    public static factory surgeAlloyF;
    public static factory baozif;
    public static GenericCrafter ksbl;
    public static void factory(){
        ksbl=new GenericCrafter("kesug"){{
            requirements(Category.crafting,with(Items.copper,23,Items.beryllium,28,GGItems.Sijingti,25,Items.lead,12,Items.plastanium,12));
            hasItems=true;
            hasLiquids=hasPower=true;
            size=4;
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));
            ambientSound = Sounds.smelter;//环境音效
            ambientSoundVolume = 0.07f;//环境音效大小
            health=1350;
            craftTime=50;
            consumePower(3.56f);
            consumeItems(with(Items.plastanium, 2,Items.metaglass,3));
            consumeLiquid(Liquids.water,(float) 1 /6);
            outputLiquid=new LiquidStack(GG_Liquids.zaos, (float) 2 /60);
        }};
        baozif=new factory("baozifsc"){{
            this.canMirror=false;
            size=2;
            health=1000;
            craftTime=90f;
            itemCapacity=20;
            requirements(Category.crafting,with(GGItems.Sijingti,22,Items.metaglass,16,Items.lead,24,Items.copper,25));
            hasPower = hasItems =hasLiquids =true;
            consumePower(0.75f);//*60
           consumeItem(Items.sporePod,3);
            craftEffect = Fx.none;
            rotate = false;//贴图不转
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"){{
                        buildingRotate = false;//贴图不转
                    }},
                    new DrawPistons(){{
                        sinMag = 1f;
                    }},
                    new DrawDefault(),
                    new DrawLiquidRegion(),
                    new DrawRegion("-top"){{buildingRotate = false;}}
            );

            outputLiquid=new LiquidStack(Liquids.oil,0.6667f);
        }};
        surgeAlloyF = new factory("surgeslloy") {{
            health =260;
            size =4;
            craftTime=120f;
            itemCapacity=20;
            requirements(Category.crafting,with(GGItems.Sijingti,22,Items.graphite,35,Items.plastanium,14,Items.copper,35,Items.carbide,12));
            hasPower = hasItems =hasLiquids =true;
            consumePower(7.5f);//*60
            addInput(Items.lead,4,Items.beryllium,4,GGItems.Sijingti,2,Liquids.water,0.1666f);
            addInput(Items.lead,4,Items.beryllium,4,Items.silicon,5,Liquids.water,0.33f);
            outputItem =new ItemStack(Items.surgeAlloy,2);
        }};
        SGfacto=new factory("plastanium-factory"){{
            health = 180;
            size=3;
            craftTime=80f;
            itemCapacity=20;
            requirements(Category.crafting, with(Items.silicon,34, GGItems.Sijingti,12,Items.metaglass,17,Items.graphite,24));
            hasPower = hasItems =hasLiquids =true;
            consumePower(3.6f);
            addInput(Items.beryllium,5,Liquids.oil,0.5f);
            outputItem = new ItemStack(Items.plastanium,1);
            outputLiquid =new LiquidStack(GG_Liquids.os,1f);
        }};

        SmallGlassKiln=new factory("smallkiln") {{
size=2;
            requirements(Category.crafting, with(Items.silicon,16,Items.beryllium,36,Items.lead,17,Items.graphite,24));
            health = 320;
            craftTime = 60f;
            itemCapacity = 20;//最大物品容量
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