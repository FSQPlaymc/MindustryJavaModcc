package content;

import mindustry.mod.Mod;

public class gzMod extends  Mod{

    // 声明炮台变量


    @Override
    public void loadContent() {
        GGItems.aloud();//加载方式
        GGNewPT.NewP();
        GG_Block.Ore();
        // 实例化 foreshadow 炮台
}}