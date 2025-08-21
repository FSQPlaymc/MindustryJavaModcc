package content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

public class GG_units extends UnitEntity {
    public static UnitType oneunit;
    public static void units(){
        oneunit=new UnitType("tizhizhu"){{
            this.speed = 0.6F;
            this.drag = 0.4F;
            this.hitSize = 13.0F;
            this.rotateSpeed = 3.0F;
            this.targetAir = false;
            this.health = 600.0F;
            this.immunities = ObjectSet.with(new StatusEffect[]{StatusEffects.burning, StatusEffects.melting});
            this.legCount = 4;
            this.legLength = 9.0F;
            this.legForwardScl = 0.6F;
            this.legMoveSpace = 1.4F;
            this.hovering = true;
            this.armor = 3.0F;
            this.ammoType = new ItemAmmoType(Items.coal);
            this.shadowElevation = 0.2F;
            this.groundLayer = 74.0F;
            this.weapons.add(new Weapon("tizhizhu-weapon") {
                {
                    this.top = false;
                    this.shootY = 3.0F;
                    this.reload = 9.0F;
                    this.ejectEffect = Fx.none;
                    this.recoil = 1.0F;
                    this.x = 7.0F;
                    this.shootSound = Sounds.flame;
                    this.bullet = new LiquidBulletType(Liquids.slag) {
                        {
                            this.damage = 13.0F;
                            this.speed = 2.5F;
                            this.drag = 0.009F;
                            this.shootEffect = Fx.shootSmall;
                            this.lifetime = 57.0F;
                            this.collidesAir = false;
                        }
                    };
                }
            });
        }};

    }
}
