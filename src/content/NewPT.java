package content;

import mindustry.content.Fx;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.RailBulletType;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Env;

import static mindustry.type.ItemStack.with;

public class NewPT {
    public static Block NGM;
    public static ItemTurret forefshadow;
    public static void NewP(){

        forefshadow = new ItemTurret("foreshadow"){{
            // 设置射程并保存引用
            float brange = range = 500f;

            // 建造需求
            requirements(Category.turret, with(
                    Items.oreZeta,10,
                    mindustry.content.Items.copper,9
            ));

            // 弹药配置
            ammo(
                    Items.oreZeta, new RailBulletType(){{
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
            maxAmmo = 40;
            ammoPerShot = 5;
            rotateSpeed = 2f;
            reload = 200f;
            ammoUseEffect = Fx.casing3Double;
            recoil = 5f;
            cooldownTime = reload;
            shake = 4f;
            size = 4;
            shootCone = 2f;
            shootSound = Sounds.railgun;
            unitSort = UnitSorts.strongest;
            envEnabled |= Env.space;

            // 生命值和冷却系统
            scaledHealth = 150;
            coolantMultiplier = 0.4f;

            // 资源消耗
            coolant = consumeCoolant(1f);
            consumePower(10f);
        }};
    }
}
