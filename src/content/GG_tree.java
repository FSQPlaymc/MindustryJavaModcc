package content;

import mindustry.content.Items;
import mindustry.content.Planets;
import mindustry.type.ItemStack;

import static content.GG_Block.GG_Powers.*;
import static content.GG_Block.GG_walls.*;
import static mindustry.content.Blocks.coreShard;
import static mindustry.content.TechTree.node;
import static mindustry.content.TechTree.nodeRoot;

public class GG_tree {
    public static void tree(){
        Planets.erekir.techTree=Planets.serpulo.techTree = nodeRoot("core-crystals", coreShard, () -> {
            node(ffff, ItemStack.with(Items.graphite,500,Items.silicon,500,GGItems.Sijingti,1000),()-> {//hongshi,ynishi,shiying,qinjingshi,linbin,lubaoshi
                node(fanying,()->{
                    node(cs);
                    node(SL);
                    node(hongshi);
                    node(ynishi);
                    node(shiying);
                    node(qinjingshi);
                    node(linbin);
                    node(lubaoshi);
                });
            });
        });
    }
}
