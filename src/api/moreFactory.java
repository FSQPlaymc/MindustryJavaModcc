package api;

import arc.scene.ui.layout.Table;

public interface moreFactory {
    void updateTile();

    // 应用当前选择的配方
    void applyCurrentRecipe();

    // 构建配置UI
    // @Override
    void buildConfiguration(Table table);

    // 保存当前配方选择
    Object config();

    // 加载保存的配方
    void configure(byte data);

    // 显示当前配方信息
    void display(Table table);
}
