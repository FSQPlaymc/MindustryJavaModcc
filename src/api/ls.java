package api;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.gen.Building;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.ui.Bar;
import mindustry.ui.ReqImage;
import mindustry.ui.Styles;
import mindustry.world.Tile;
import mindustry.world.blocks.production.GenericCrafter;
import api.NewRecipe;


public class ls extends GenericCrafter {
    public Seq<NewRecipe> recipes = new Seq<>();
    public ls(String name) {
        super(name);
        configurable = true; // 必须启用配置功能才能显示按钮
        saveConfig = true;
        sync = true; // 添加同步支持
    }
    // 配方数据结构
    // 添加多流体输入输出配方的方法
    public void addRecipe(String name,
                          ItemStack[] inputItems,
                          LiquidStack[] inputLiquids,
                          ItemStack[] outputItems,
                          LiquidStack[] outputLiquids,
                          float craftTime,
                          float powerUse) {
        recipes.add(new NewRecipe(name, inputItems, inputLiquids, outputItems, outputLiquids, craftTime, powerUse));
    }

    // 简化添加方法（单物品+多流体）
    public void addRecipe(String name,
                          ItemStack inputItem,
                          LiquidStack[] inputLiquids,
                          ItemStack outputItem,
                          LiquidStack[] outputLiquids,
                          float craftTime,
                          float powerUse) {
        addRecipe(name,
                new ItemStack[]{inputItem},
                inputLiquids,
                new ItemStack[]{outputItem},
                outputLiquids,
                craftTime, powerUse);
    }
    public class CustomCrafterBuild extends GenericCrafterBuild {
        // 当前选中的配方索引
        public int currentRecipe = 0;

        @Override
        public void updateTile() {
            // 应用当前选中的配方
            applyCurrentRecipe();
            super.updateTile();
        }

        // 应用当前配方的属性
            private void applyCurrentRecipe() {
                if (recipes.isEmpty()) return;

                currentRecipe = Mathf.clamp(currentRecipe, 0, recipes.size - 1);
                NewRecipe recipe = recipes.get(currentRecipe);

                // 更新基础属性
                craftTime = recipe.craftTime;
                outputItem = recipe.outputItems.length > 0 ? recipe.outputItems[0] : null;

                // 重置所有消耗
                consume.clear();

                // 处理物品输入
                if (recipe.inputItems != null) {
                    for (ItemStack stack : recipe.inputItems) {
                        consumeItem(stack.item, stack.amount);
                    }
                }

                // 处理多流体输入
                if (recipe.inputLiquids != null) {
                    for (LiquidStack stack : recipe.inputLiquids) {
                        consumeLiquid(stack.liquid, stack.amount);
                    }
                }

                // 处理电力消耗
                consumePower(recipe.powerUse);
            }

            @Override
            public void craft() {
                super.craft();
                NewRecipe recipe = recipes.get(currentRecipe);

                // 处理多流体输出
                if (recipe.outputLiquids != null) {
                    for (LiquidStack stack : recipe.outputLiquids) {
                        // 尝试向周围管道输出流体
                        for (var dir : Direction.values()) {
                            Tile adjacent = tile.adjacent(dir);
                            if (adjacent != null && adjacent.build != null) {
                                Building other = adjacent.build;
                                if (other.acceptLiquid(this, stack.liquid)) {
                                    float accepted = other.handleLiquid(this, stack.liquid, stack.amount);
                                    if (accepted > 0) break;
                                }
                            }
                        }
                        // 若无法输出则存储在自身（如果有液体容量）
                        if (hasLiquids) {
                            liquids.add(stack.liquid, stack.amount);
                        }
                    }
                }
            }
            // 更新UI显示多流体信息
            @Override
            public void buildConfiguration(Table table) {
                table.table(Styles.black5, t -> {
                    t.add("配方选择").color(Color.yellow).center().row();
                    t.image().color(Color.gray).height(2f).fillX().pad(5f).row();

                    for (int i = 0; i < recipes.size; i++) {
                        int index = i;
                        NewRecipe recipe = recipes.get(i);

                        t.button(btn -> {
                                    btn.table(b -> {
                                        b.add(recipe.name).color(index == currentRecipe ? Color.green : Color.white).row();

                                        // 显示物品输入
                                        if (recipe.inputItems != null && recipe.inputItems.length > 0) {
                                            b.table(inputs -> {
                                                inputs.add("物品输入: ");
                                                for (ItemStack stack : recipe.inputItems) {
                                                    inputs.add(new ReqImage(stack.item.uiIcon,
                                                                    () -> items.has(stack.item, stack.amount)))
                                                            .size(20f).pad(2f);
                                                    inputs.add(stack.amount + " ").padRight(4f);
                                                }
                                            }).left().row();
                                        }

                                        // 显示多流体输入
                                        if (recipe.inputLiquids != null && recipe.inputLiquids.length > 0) {
                                            b.table(liquids -> {
                                                liquids.add("流体输入: ");
                                                for (LiquidStack stack : recipe.inputLiquids) {
                                                    liquids.add(new ReqImage(stack.liquid.uiIcon,
                                                                    () -> this.liquids.get(stack.liquid) >= stack.amount))
                                                            .size(20f).pad(2f);
                                                    liquids.add(stack.amount + "/t ").padRight(4f);
                                                }
                                            }).left().row();
                                        }

                                        // 显示物品输出
                                        if (recipe.outputItems != null && recipe.outputItems.length > 0) {
                                            b.table(outputs -> {
                                                outputs.add("物品输出: ");
                                                for (ItemStack stack : recipe.outputItems) {
                                                    outputs.image(stack.item.uiIcon).size(20f).pad(2f);
                                                    outputs.add(stack.amount + " ").padRight(4f);
                                                }
                                            }).left().row();
                                        }

                                        // 显示多流体输出
                                        if (recipe.outputLiquids != null && recipe.outputLiquids.length > 0) {
                                            b.table(outputs -> {
                                                outputs.add("流体输出: ");
                                                for (LiquidStack stack : recipe.outputLiquids) {
                                                    outputs.image(stack.liquid.uiIcon).size(20f).pad(2f);
                                                    outputs.add(stack.amount + "/t ").padRight(4f);
                                                }
                                            }).left().row();
                                        }
                                    });
                                }, Styles.flatToggleMenut, () -> {
                                    currentRecipe = index;
                                    applyCurrentRecipe();
                                    configure((byte) index);
                                }).update(btn -> btn.setChecked(index == currentRecipe))
                                .pad(2f).fillX().row();
                    }
                }).fillX();
            }
            // 显示流体容量信息
            @Override
            public void displayBars(Table table) {
                super.displayBars(table);
                // 显示当前流体存储状态
                if (hasLiquids && liquids != null) {
                    for (LiquidStack stack : liquids) {
                        if (stack.amount > 0.001f) {
                            table.add(new Bar(
                                    () -> stack.liquid.localizedName,
                                    () -> stack.liquid.color,
                                    () -> stack.amount / liquidCapacity
                            )).growX().row();
                        }
                    }
                }
        }
    }
}