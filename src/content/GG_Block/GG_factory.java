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
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Env;

import static mindustry.type.ItemStack.with;

public class GG_factory {
    public static AttributeCrafter cultivator;
    public static factory SmallGlassKiln;
    public static factory SGfacto;
    public static factory surgeAlloyF;
    public static factory baozif,TnmXinpian;
    public static GenericCrafter ksbl,sitichun,Ctiqu;
    public static void factorys(){
        cultivator = new AttributeCrafter("cultivator"){{
            requirements(Category.production, with(Items.graphite,24, GGItems.Sijingti, 25, Items.silicon, 10,Items.plastanium,12));
            outputItem = new ItemStack(Items.sporePod, 5);
            craftTime = 100;
            size = 3;
            hasLiquids = true;
            hasPower = true;
            hasItems = true;

            liquidCapacity = 80f;
            craftEffect = Fx.none;
            envRequired |= Env.spores;
            attribute = Attribute.spores;

            legacyReadWarmup = true;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.water),
                    new DrawDefault(),
                    new DrawCultivator(),
                    new DrawRegion("-top")
            );
            maxBoost = 2f;

            consumePower(320f / 60f);
            consumeLiquid(Liquids.water, 54f / 60f);
        }};
        TnmXinpian=new factory("纳米芯片打印机"){{
            hasItems=hasPower=true;
            requirements(Category.crafting,with(Items.beryllium,74,Items.graphite,57,Items.lead,74,GGItems.Sijingti,58,Items.metaglass,60,Items.titanium,40));
            size=3;
            craftTime=60f;
            consumePower(73/60f);
            addInput(Items.graphite,4,Items.titanium,6,Items.silicon,8);//配方A：4石墨，6钛，8硅;配方B：6石墨，4铍，12硅
            addInput(Items.graphite,6,Items.beryllium,4,Items.silicon,12);
            outputItem=new ItemStack(GGItems.TanNaMiHeXing,1);
        }};
        Ctiqu=new GenericCrafter("碳提取器"){{
            hasItems=hasLiquids=hasPower=true;
            requirements(Category.crafting,with(Items.copper,26,Items.beryllium,14,Items.graphite,13,Items.lead,34,GGItems.Sijingti,18));
            size=3;
            craftTime=30f;
            consumePower(73/30f);
            consumeItem(GGItems.ThuaKuangShi,5);
            outputItem=new ItemStack(Items.graphite,4);
            outputLiquid=new LiquidStack(Liquids.slag, (float) 2 /3);
        }};
        sitichun=new GenericCrafter("guitichunchang"){{
            requirements(Category.crafting,with(Items.copper,43,Items.beryllium,38,GGItems.Sijingti,50,Items.lead,32,Items.plastanium,12));
            size=3;
            itemCapacity =50;//最大物品容量
            health=500;
            craftTime=120;
            hasItems=true;
            hasPower=true;
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffce4a")));
            consumePower(50/6f);
            consumeItems(with(Items.sporePod,6,Items.coal,16,Items.lead,18,Items.sand,22));
            outputItem=new ItemStack(Items.silicon,20);

        }};
        ksbl=new GenericCrafter("kesug"){{
            itemCapacity = 30;//最大物品容量
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
            outputLiquid=new LiquidStack(GG_Liquids.zaisuye, (float) 2 /60);
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