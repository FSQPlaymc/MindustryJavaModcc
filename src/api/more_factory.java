package api;
import arc.graphics.Color;

import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.ui.Styles;
import mindustry.world.blocks.production.GenericCrafter;


public class more_factory extends GenericCrafter {
    // 可用配方列表
    public Seq<Recipe> recipes = new Seq<>();
    // 默认配方索引
    public int defaultRecipe = 0;

    public more_factory(String name) {
        super(name);

        // 基础配置
//        size = 3;
//        health = 800;
//
//        // 添加默认配方
//        recipes.add(new Recipe(
//                "基础合成", // 配方名称
//                ItemStack.with(Items.copper, 3, Items.lead, 2), // 输入
//                new ItemStack(Items.metaglass, 2), // 输出
//                60f, // 生产时间
//                2f // 电力消耗
//        ));

        // 配置UI更新
        sync = true; // 添加同步支持
        configurable = true;
        saveConfig = true;
    }

    // 配方数据结构
    public static class Recipe {
        public String name;
        public ItemStack[] input;
        public ItemStack output;
        public float craftTime;
        public float powerUse;
        public LiquidStack liquid;

        public Recipe(String name, ItemStack[] input, ItemStack output, float craftTime, float powerUse) {
            this.name = name;
            this.input = input;
            this.output = output;
            this.craftTime = craftTime;
            this.powerUse = powerUse;
        }

        // 从物品堆栈创建
        public Recipe(String name, ItemStack input, ItemStack output, float craftTime, float powerUse) {
            this(name, new ItemStack[]{input}, output, craftTime, powerUse);
        }
    }

    // 工厂建筑类
    public class moreFactory extends GenericCrafterBuild implements api.moreFactory {
        // 当前选择的配方索引
        public int currentRecipe = 0;


        @Override
        public void updateTile() {
            applyCurrentRecipe();
            super.updateTile();

        }

        public void applyCurrentRecipe() {
            if (recipes.size > 0) {
                Recipe recipe = recipes.get(currentRecipe);

                // 设置消耗和产出
                //consume.clear();
                for (ItemStack Object : recipe.input) {
                    consumers.equals(Object);
                }

                // 设置电力消耗
                consumePower(recipe.powerUse);

                // 设置产出
                outputItem = recipe.output;

                // 设置生产时间
                craftTime = recipe.craftTime;
            }
        }

        private void consPower(float powerUse) {
        }

        // 构建配置UI
        // @Override
        @Override
        public void buildConfiguration(Table table) {
            table.table(Styles.black5, buttons -> {
                buttons.defaults().size(140f, 50f).pad(5f);

                // 标题
                buttons.add("配方选择").color(Color.yellow).center().row();

                // 修复: 使用自定义分隔线替代 separator()
                buttons.image() // 添加分隔线
                        .color(Color.gray)
                        .height(4f).fillX().pad(5f).row();

                for (int i = 0; i < recipes.size; i++) {
                    int index = i;
                    Recipe recipe = recipes.get(i);

                    buttons.button(button -> {
                        button.table(bt -> {
                            // 配方名称
                            bt.add(recipe.name).color(
                                    index == currentRecipe ?
                                            Color.green : Color.lightGray
                            ).row();

                            // 输入显示
                            bt.table(inputs -> {
                                inputs.add("输入: ");
                                for (ItemStack input : recipe.input) {
                                    inputs.image(input.item.uiIcon)
                                            .size(24f).padRight(2f);
                                    inputs.add(input.amount + " ").left();
                                }
                            }).left().row();

                            // 输出显示
                            bt.table(outputs -> {
                                outputs.add("输出: ");
                                outputs.image(recipe.output.item.uiIcon)
                                        .size(24f).padRight(2f);
                                outputs.add(recipe.output.amount + " ").left();
                            }).left();

                            // 液体消耗显示
                            if (recipe.liquid != null) {
                                bt.table(liquids -> {
                                    liquids.add("液体: ");
                                    liquids.image(recipe.liquid.liquid.uiIcon)
                                            .size(24f).padRight(2f);
                                    liquids.add(recipe.liquid.amount + "/秒").left();
                                }).left();
                            }
                        }).left();
                    }, Styles.flatToggleMenut, () -> {
                        currentRecipe = index;
                        applyCurrentRecipe();
                        configure((byte)index); // 通知配置变化
                    }).update(b -> b.setChecked(index == currentRecipe)).row();
                }

                // 当前选择显示
                buttons.image() // 添加分隔线
                        .color(Color.gray)
                        .height(4f).fillX().pad(5f).row();

                buttons.add("当前: " + recipes.get(currentRecipe).name)
                        .color(Color.green).padTop(10f).row();
            }).fillX();
        }

        // 保存当前配方选择
        @Override
        public Object config() {
            return (byte) currentRecipe;
        }

        // 加载保存的配方
        @Override
        public void configure(byte data) {
            currentRecipe = data & 0xff;
            if (currentRecipe >= recipes.size) {
                currentRecipe = 0;
            }
            applyCurrentRecipe();
        }

        // 显示当前配方信息
        @Override
        public void display(Table table) {
            super.display(table);

            Recipe recipe = recipes.get(currentRecipe);

            table.row();
            table.add("[lightgray]当前配方: [accent]" + recipe.name).left();
            table.row();
            table.add("[lightgray]生产时间: [accent]" + recipe.craftTime / 60f + "秒").left();
        }
    }

    // 在工厂类中添加配方
    public void addRecipe(String name, ItemStack[] input, ItemStack output, float craftTime, float powerUse) {
        recipes.add(new Recipe(name, input, output, craftTime, powerUse));
    }

    // 添加带液体消耗的配方
    public void addRecipe(String name, ItemStack[] input, ItemStack output,
                          float craftTime, float powerUse, LiquidStack liquid) {
        Recipe recipe = new Recipe(name, input, output, craftTime, powerUse);
        recipe.liquid = liquid; // 扩展Recipe类添加liquid字段
        recipes.add(recipe);
    }
}
