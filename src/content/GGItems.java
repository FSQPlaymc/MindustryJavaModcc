package content;

import arc.Core;
import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.type.Item;

import static mindustry.content.Items.*;

public class GGItems {
    public static Item zeta;
    public static Item itemCZzw;
    public static Item Sijingti;
    public static Item ThuaKuangShi;
    public static Item TanNaMiHeXing;
    public static Item zaisu;
    public static  Item hejing;
    public static final Seq<Item> GGItemsj = new Seq();
    public GGItems(){}
    public static void aloud(){
        Sijingti = new Item("guijingti", Color.valueOf("031817")) {{
            this.hardness = 2;
            this.explosiveness = 0.0F;//爆炸 = 0.0F;可燃性
            this.flammability = 0.0F;
            this.charge = 0.0F;//电性
        }};
        ThuaKuangShi = new Item("tanhuakuangshi",Color.valueOf("b81163")){{
            this.hardness =3;
            this.flammability = 2.0f;
            this.radioactivity = 1.55f;//放射性
        }};
        TanNaMiHeXing = new Item("tanmnimihexing",Color.valueOf("0bb330")){{
            this.hardness = 0;
        }};
        zaisu = new Item("zaisu",Color.valueOf("3661fb")){{
            this.flammability = 0.5f;
            this.explosiveness = 1.0f;
            this.charge =250.0f;
        }};
        hejing=new Item("hexingjingti",Color.valueOf("a227ec")){{
            this.flammability= 3.0f;
            this.explosiveness = 15.0f;
            this.radioactivity = 13.0f;
            this.charge = 30.0f;
        }};
        zeta=new Item("Zeta", Color.valueOf("e07123")){{//come from NewHorizon
            this.hardness=4;
        this.flammability=0.5f;
        this.explosiveness=99.9f;
        this.radioactivity=222.2f;
        this.fullIcon = Core.atlas.find("Zeta");
        }};
        itemCZzw=new Item("Zwangzaozhiwu",Color.valueOf("ed0e21")){{
            this.hardness=90;
            this.explosiveness=1000;
            this.charge=1000;
            this.fullIcon = Core.atlas.find("Zwangzaozhiwu");

        }};
        GGItemsj.addAll(new Item[]{scrap, copper, lead, graphite, coal, titanium, thorium, silicon, plastanium,
                phaseFabric, surgeAlloy, sporePod, sand, blastCompound, pyratite, metaglass,itemCZzw,hejing,ThuaKuangShi,TanNaMiHeXing,
                Sijingti});
    }

}
/*
差点忘了
核晶
燃30%
放130%
爆150%
电300%
public Color color; //颜色
public float explosiveness; //爆炸性
public float flammability; //可燃性
public float radioactivity; //放射性
public float charge; //电荷量
public int hardness; //硬度
public float cost; //成本
public float healthScaling; //生命值缩放比例
public boolean lowPriority; //低优先级
public int frames; //帧数
public int transitionFrames; //过渡帧数
public float frameTime; //帧时间
public boolean buildable; //可建造
public boolean hidden; //隐藏状态
        this.explosiveness = 0.0F;
        this.flammability = 0.0F;
        this.charge = 0.0F;
        this.hardness = 0;
        this.cost = 1.0F;
        this.healthScaling = 0.0F;
        this.frames = 0;
        this.transitionFrames = 0;
        this.frameTime = 5.0F;
        this.buildable = true;
        this.hidden = false;
        this.color = color;
爆炸性 = 0.0F;
可燃性 = 0.0F;
电荷 = 0.0F;
硬度 = 0;
成本 = 1.0F;
健康缩放 = 0.0F;
帧 = 0;
过渡帧 = 0;
帧时间 = 5.0F;
可构建 = true;
隐藏 = false;
color = 颜色;*/