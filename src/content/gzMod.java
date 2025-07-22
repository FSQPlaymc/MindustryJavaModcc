package content;

import content.GG_Block.GG_Block;
import content.GG_Block.GG_Powers;
import mindustry.mod.Mod;

public class gzMod extends  Mod{

    // 声明炮台变量


    @Override
    public void loadContent() {
        GGItems.aloud();//加载方式
        GGNewPT.NewP();
        GG_Block.Ore();
        GG_Powers.Power();
        //Itemschange.chi();
        // 实例化 foreshadow 炮台
}}