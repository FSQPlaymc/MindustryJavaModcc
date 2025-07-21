package content;

import example.Itemschange;
import mindustry.mod.Mod;

public class gzMod extends  Mod{

    // 声明炮台变量


    @Override
    public void loadContent() {
        GGItems.aloud();//加载方式
        GGNewPT.NewP();
        GG_Block.Ore();
        //Itemschange.chi();
        // 实例化 foreshadow 炮台
}}