package content;

import api.gailubao;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Planets;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;

import static content.GG_Block.GG_Block.*;
import static content.GG_Block.GG_factory.*;
import static content.GG_Block.GG_Powers.*;
import static content.GG_Block.GG_walls.*;
import static content.GG_units.*;
import static mindustry.content.Blocks.coreShard;
import static mindustry.content.TechTree.node;
import static mindustry.content.TechTree.nodeRoot;

public class GG_tree {
    public static void tree(){
        Planets.erekir.techTree=Planets.serpulo.techTree = nodeRoot("core-crystals", coreShard, () -> {
            node(groundFactory,ItemStack.with(Items.copper,2000,Items.lead,1000,Items.graphite,700,Items.silicon,500),()->{
                node(tizhizhu,ItemStack.with(Items.copper,900,Items.lead,500,Items.graphite,300,Items.silicon,200,GGItems.Sijingti,300),()->{});
            });
            node(pulverizer,ItemStack.with(Items.copper,500,Items.lead,500,Items.graphite,200),()->{
                node(separator,ItemStack.with(Items.graphite,900,Items.silicon,600,GGItems.Sijingti,200),()->{});
                node(GGxiaoxinggui,ItemStack.with(Items.copper,2000,Items.lead,2000,Items.graphite,1000),()->{
                    node(SGfacto,ItemStack.with(Items.tungsten,1000,Items.graphite,2000,GGItems.Sijingti,1000,Items.silicon,2000),Seq.with(new Objectives.Research(Items.tungsten)),()->{});
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
            //物品树-------------------------------------------------------------------------------------------------------------------------
                node(GGItems.ThuaKuangShi);
                node(GGItems.Sijingti,()->{
                    node(GGItems.TanNaMiHeXing, Seq.with(new Objectives.Research(GGItems.ThuaKuangShi)),()->{});
                });
        });
    }
}
