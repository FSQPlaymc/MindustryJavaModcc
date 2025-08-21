package content.GG_Block;

import api.block.power.NC_power;
import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.consumers.ConsumeItemExplode;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.draw.*;

public class GG_Powers {
    public static NC_power ffff;
    public  static ConsumeGenerator reanshaoshi;
    public static SolarGenerator solarPanel;
    public static void Power(){
        ffff=new NC_power("ffff"){{
            requirements(Category.power, ItemStack.with(Items.lead, 20, Items.silicon, 32));
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.24f;
            size=2;
            health = 700;
            itemDuration = 360f;
            powerProduction = 15f;
            heating = 0.02f;
            this.basepower=1.3f;
            drawer = new DrawMulti(new DrawDefault(),  new DrawRegion("-top"){{
                buildingRotate = false;//贴图不转
            }});
            consumeItem(Items.thorium);
            consumeLiquid(Liquids.cryofluid, heating / coolantPower).update(false);
        }};
         solarPanel = new SolarGenerator("GG-solar-panel") {{
            requirements(Category.power, ItemStack.with(Items.lead, 20, Items.silicon, 32));
            powerProduction = 0.72f;
            this.size=2;
        }};
        reanshaoshi = new ConsumeGenerator("steam-generator"){{
            requirements(Category.power, ItemStack.with(Items.copper, 35, Items.graphite, 25, Items.lead, 40, Items.silicon, 30));
            powerProduction = 5.5f;
            itemDuration = 90f;
            consumeLiquid(Liquids.water, 0.1f);
            hasLiquids = true;
            size = 2;
            generateEffect = Fx.generatespark;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;
            // 消耗物品条件系统：
            consume(new ConsumeItemFlammable());  // 要求输入物品具有可燃性属性
            consume(new ConsumeItemExplode());    // 要求输入物品具有爆炸性属性

            /*drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawWarmupRegion(),
                    new DrawRegion("-turbine"){{
                        rotateSpeed = 2f;
                    }},
                    new DrawRegion("-turbine"){{
                        rotateSpeed = -2f;
                        rotation = 45f;
                    }},
                    new DrawRegion("-cap"),
                    new DrawLiquidRegion()
            );*/
        }};
    }
}
