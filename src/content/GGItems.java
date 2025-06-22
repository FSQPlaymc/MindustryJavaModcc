package content;

import arc.graphics.Color;
import mindustry.type.Item;

public class GGItems {
    public static Item oreZeta;
    public static Item oreCZzw;
    public static void aloud(){
        oreZeta=new Item("Zeta", Color.valueOf("e07123")){{
            this.hardness=2;
        this.flammability=0.5f;
        this.explosiveness=99.9f;
        this.radioactivity=222.2f;
        }};
        oreCZzw=new Item("Zwangzaozhiwu",Color.valueOf("ed0e21")){{
            this.hardness=90;
            this.explosiveness=1000;
            this.charge=1000;
        }};

    }

}
/*public Color color; //颜色
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