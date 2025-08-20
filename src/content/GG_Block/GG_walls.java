package content.GG_Block;

import api.GG_Wall;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.Wall;

public class GG_walls {
    public static Wall cs;
    public static Wall glass;
    public static Wall SL;
    public static GG_Wall hongshi;
    public static Wall fanying,jansuji;
    public static void walls(){
        hongshi= new GG_Wall("hongshi") {{
            requirements(Category.defense, ItemStack.with(Items.lead, 20));
            size=2;
            this.colod=0.9f;
        }};
        jansuji=new Wall("jansuji"){{
            requirements(Category.defense, ItemStack.with(Items.coal, 20,Items.copper,10));
            size=2;}};
        glass=new Wall("csglass"){{
            requirements(Category.defense, ItemStack.with(Items.lead, 20));
            size=2;
        }};
        fanying=new Wall("fanying"){{
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
