package content.GG_Block;


import api.factory;
import api.gailubao;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Interp;
import arc.struct.Seq;
import content.GGItems;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.Effect;
import mindustry.graphics.Layer;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawMulti.*;

import static content.GGItems.Sifenmo;
import static content.GGItems.Sijingti;
import static mindustry.type.ItemStack.with;

public class GG_Block {

    public static GenericCrafter chuangZaolu;
    public static gailubao.gailubaoFactory GGxiaoxinggui;
    public static OreBlock oreZeta;
    public static OreBlock oreThuawu;
    public static OreBlock oreGuijingti;
    public static OreBlock oreCarbide;
    public static OreBlock oreSurge;
    public static OreBlock oreHejing;
    public static Drill luoxuanzuan;
    public static void Ore(){
        luoxuanzuan =new Drill("luoxuanzuan"){{
            requirements(Category.production, with(Items.copper, 35, Items.graphite, 30, Items.silicon, 30, Items.titanium, 20));
            drillTime = 280;
            size = 3;
            hasPower = true;
            tier = 4;
            updateEffect = Fx.pulverizeMedium;
            drillEffect = Fx.mineBig;
            blockedItems = Seq.with(Sifenmo);//不挖的物品

            consumePower(1.10f);
            consumeLiquid(Liquids.water, 0.08f).boost();
        }};
        factory sandCracker = new factory("sand-cracker") {{
            size = 2;
            requirements(Category.crafting, with());
            health = 320;
            craftTime = 60f;
            itemCapacity = 60;
            hasPower = hasItems = true;
            updateEffect = new Effect(80f, e -> { // 持续80帧的动态粒子效果
                Fx.rand.setSeed(e.id); // 基于实体ID的随机种子
                // 颜色插值：浅灰→灰，随进度变化
                Draw.color(Color.lightGray, Color.gray, e.fin());
                // 生成4个方向随机的粒子
                Angles.randLenVectors(e.id, 4, 2.0F + 12.0F * e.fin(Interp.pow3Out), (x, y) -> {
                    // 绘制圆形粒子，尺寸随进度减小
                    Fill.circle(e.x + x, e.y + y, e.fout() * Fx.rand.random(1, 2.5f));
                });
            }).layer(Layer.blockOver + 1); // 渲染层级：建筑上层
addInput(GGItems.zeta,4);
            outputItem = new ItemStack(Items.sand, 12);
        }};
        oreHejing = new OreBlock("hejing-wall", GGItems.hejing){{//巨浪合金
            this.variants = 1;
        }};
        oreSurge = new OreBlock("surge-wall",Items.surgeAlloy){{//巨浪合金
            wallOre =true;
            this.variants = 1;
        }};
        oreCarbide =new OreBlock("carbide-wall",GGItems.ThuaKuangShi){{
            wallOre =true;
            this.variants = 1;
        }};
        oreCarbide =new OreBlock("carbide",GGItems.ThuaKuangShi){{
            this.variants = 1;
        }};

        oreGuijingti = new OreBlock("silicon-crystal-slag-wall"){{
            itemDrop = Sifenmo;
            wallOre = true;//墙矿
        }};
        oreGuijingti = new OreBlock("silicon-crystal-slag"){{
            itemDrop = Sifenmo;
            variants = 2;
        }};
        GGxiaoxinggui =new gailubao.gailubaoFactory("xiaoxinggui"){{
            requirements(Category.crafting, with(GGItems.Sijingti,150));
            outputItem = new ItemStack(Items.silicon,1);
            craftTime = 60f;
            itemCapacity = 20;
            size =3;
            hasItems = true;//消耗物品
            hasLiquids = false;//消耗流体
            hasPower = true;//消耗电力
            craftEffect = Fx.trailFade;
            consumePower(0.45f);//*100
            consumeItem(Sijingti, 5);
        }};
        oreThuawu = new OreBlock("orethuawu-wall"){{
            itemDrop = GGItems.ThuaKuangShi;
           oreDefault = true;
            //oreThreshold = 0.23f;
           // oreScale = 9.0f;
            wallOre = true;//墙矿
        }};
        oreThuawu = new OreBlock("orethuawu"){{
            itemDrop = GGItems.ThuaKuangShi;
            oreDefault = true;
            //oreThreshold = 0.23f;
            // oreScale = 9.0f;
            //wallOre = true;//墙矿
        }};


        oreZeta=new OreBlock("zeta"){{
            itemDrop = GGItems.zeta; // 掉落物（必须）
          //  oreDefault = true;
           // oreThreshold = 0.71f;//控制矿石生成的稀疏程度。值越大（越接近1），矿石越稀少；值越小（越接近0），矿石越密集。
           // oreScale = 10.47619f;//控制矿石分布图案的频率。值越大，矿石分布越密集（图案更小更频繁）；值越小，矿石分布越稀疏（图案更大更平缓）
            variants = 3;//三种贴图在blocks/environment中
            //fullIcon=Core.atlas.find("custom-ores/ore_zeta");
        }};
        chuangZaolu = new GenericCrafter("chuangzaolu"){{//贴图在
        requirements(Category.crafting, with(GGItems.zeta,30,
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
二者必选其一否则矿石无法显示贴图。*/