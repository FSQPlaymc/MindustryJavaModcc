package api;

import api.type.Recipe;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.ui.Styles;
import mindustry.world.blocks.production.GenericCrafter;


public class ls extends GenericCrafter {
    public Seq<Recipe> recipes = new Seq<>();
    public ls(String name) {
        super(name);
        configurable = true; // 必须启用配置功能才能显示按钮
    }
    public void addInputs(Object...objects) {
        Recipe recipe = new Recipe(objects);
        recipes.add(recipe);
    }
    public class CustomCrafterBuild extends GenericCrafterBuild {

        // 按钮点击逻辑示例：重置生产进度
        private void resetProgress() {
            progress = 0f; // 重置生产进度
            Log.info("生产进度已重置"); // 日志输出
        }

        @Override
        public void buildConfiguration(Table table) {
            // 调用父类方法（如果有默认配置UI的话）
            super.buildConfiguration(table);

            // 添加一个分隔线
            table.image().color(arc.graphics.Color.gray).height(2f).fillX().pad(5f).row();

            // 添加自定义按钮
            // 创建按钮区域
            table.table(Styles.black5, buttons -> {
            buttons.defaults().wrap().size(140f, 50f).pad(5f); // 统一设置按钮大小和边距// 设置按钮大小
            for (int x = 1; x < 20; x = x+1) {
                if (x % 10 == 0){
                    buttons.button("按钮"+ x, Styles.defaultt, () -> {
                        // 按钮1逻辑
                    }).row();//.row(); // 调用row()实现换行
                }else {
                    buttons.button("按钮"+ x, Styles.defaultt, () -> {
                        // 按钮2逻辑
                    });
                }
            }
        }).fillX();//必加
    }
}
}