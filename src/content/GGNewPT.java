package content;

import arc.Core;
import mindustry.content.Fx;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.RailBulletType;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Env;

import static mindustry.type.ItemStack.with;

public class GGNewPT {
    public static Block NGM;
    public  static ItemTurret xiaogangpao;
    public static ItemTurret forefshadow;
    public static void NewP(){
        xiaogangpao=new ItemTurret("gp"){{
            fullIcon = Core.atlas.find("gp");
            requirements(Category.turret,with(mindustry.content.Items.metaglass,1145,
                    GGItems.oreZeta,1145,
                    mindustry.content.Items.copper,1145
            ));
            ammo(
                    GGItems.oreCZzw,new BasicBulletType(3.5f,15){{//speed：飞行速度 damage参数表示子弹的基础伤害值
                        pierceDamageFactor=0.7f;
                        buildingDamageMultiplier=2.0f;
                        height=8f;
                        width=8f;
                lifetime=900;
                damage=114514;
                hitEffect = despawnEffect = Fx.hitBulletColor;

                    }}
            );
            size=1;
            range = 114514;//射程
            ammoPerShot = 1;             // 每次射击消耗弹药
            rotateSpeed = 3f;            // 旋转速度
            // 生命值和冷却系统
            scaledHealth = 1500;
            coolantMultiplier = 0.8f;
            coolant = consumeCoolant(1f);
            consumePower(10f);

        }};

        forefshadow = new ItemTurret("foreshadow"){{
            // 设置射程并保存引用
            float brange = range = 500f;

            // 建造需求
            requirements(Category.turret, with(
                    GGItems.oreZeta,10,
                    mindustry.content.Items.copper,9
            ));

            // 弹药配置
            ammo(
                    GGItems.oreZeta, new RailBulletType(){{
                        // 特效设置
                        shootEffect = Fx.instShoot;
                        hitEffect = Fx.instHit;
                        pierceEffect = Fx.railHit;
                        smokeEffect = Fx.smokeCloud;
                        pointEffect = Fx.instTrail;
                        despawnEffect = Fx.instBomb;

                        // 子弹属性
                        pointEffectSpace = 20f;
                        damage = 1350;
                        buildingDamageMultiplier = 0.2f;
                        maxDamageFraction = 0.6f;
                        pierceDamageFactor = 1f;
                        length = brange; // 使用之前设置的射程
                        hitShake = 6f;
                        ammoMultiplier = 1f;
                    }}
            );

            // 炮台属性
            maxAmmo = 40;                // 最大弹药量
            ammoPerShot = 5;             // 每次射击消耗弹药
            rotateSpeed = 2f;            // 旋转速度
            reload = 200f;               // 装弹时间
            ammoUseEffect = Fx.casing3Double;  // 弹药使用特效
            recoil = 5f;                 // 后坐力
            cooldownTime = reload;       // 冷却时间
            shake = 4f;                  // 震动强度
            size = 4;                    // 尺寸
            shootCone = 2f;              // 射击散布角度
            shootSound = Sounds.railgun; // 射击音效
            unitSort = UnitSorts.strongest; // 单位排序方式
            envEnabled |= Env.space;     // 可用环境(太空)


            // 生命值和冷却系统
            scaledHealth = 150;
            coolantMultiplier = 0.4f;

            // 资源消耗
            coolant = consumeCoolant(1f);
            consumePower(10f);
        }};
    }
}
