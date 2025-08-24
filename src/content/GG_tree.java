package content;

import api.gailubao;
import mindustry.content.Items;
import mindustry.content.Planets;
import mindustry.type.ItemStack;

import static content.GG_Block.GG_Block.*;
import static content.GG_Block.GG_factory.*;
import static content.GG_Block.GG_Powers.*;
import static content.GG_Block.GG_walls.*;
import static mindustry.content.Blocks.coreShard;
import static mindustry.content.TechTree.node;
import static mindustry.content.TechTree.nodeRoot;

public class GG_tree {
    public static void tree(){
        Planets.erekir.techTree=Planets.serpulo.techTree = nodeRoot("core-crystals", coreShard, () -> {
            node(pulverizer,ItemStack.with(Items.copper,500,Items.lead,500,Items.graphite,200),()->{
                node(GGxiaoxinggui,ItemStack.with(Items.copper,2000,Items.lead,2000,Items.graphite,1000),()->{
                    node(Sichunghua,ItemStack.with(Items.titanium,2000,Items.silicon,3000,Items.graphite,1000),()->{});
                });
            });
            node(ffff, ItemStack.with(Items.graphite,500,Items.silicon,500,GGItems.Sijingti,1000),()-> {//hongshi,ynishi,shiying,qinjingshi,linbin,lubaoshi
                node(cs,()->{
                    node(fanying);
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
