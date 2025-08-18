package content.GG_Block;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.Wall;

public class GG_walls {
    public static Wall cs;
    public static Wall glass;
    public static Wall SL;
    public static Wall fanying;
    public static void walls(){
        glass=new Wall("csglass"){{
            requirements(Category.defense, ItemStack.with(Items.lead, 20));
            size=2;
        }};
        fanying=new Wall("fanyingdanyuan"){{
            requirements(Category.defense, ItemStack.with(Items.lead, 20));
            size=2;
        }};
        cs =new Wall("cs"){{
            requirements(Category.defense, ItemStack.with(Items.lead, 20));
            size=2;
        }};
        SL =new Wall("sl"){{
            requirements(Category.defense, ItemStack.with(Items.lead, 20));
            size=2;
        }};
    }
}
