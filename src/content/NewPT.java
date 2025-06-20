package content;

import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class NewPT {
    public static Block NGM;
    public static void NewP(){
        NGM=new ItemTurret("capt"){{
        requirements(Category.turret, ItemStack.with(Items.oreZeta,91));
        ammo(
              Items.oreZeta, new BasicBulletType(2.9f,9){{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    ammoMultiplier = 2;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                  //  hitColor = backColor = trailColor = Pal.copperAmmoBack;
                  //  frontColor = Pal.copperAmmoFront;
                }}
        );
        }};
    }
}
