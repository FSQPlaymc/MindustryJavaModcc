package api;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import mindustry.gen.Building;
//import mindustry.gen.Direction;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.ui.Bar;
import mindustry.ui.ReqImage;
import mindustry.ui.Styles;
import mindustry.world.Tile;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.Consume;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumePower;

public class ls extends GenericCrafter {
    public Seq<NewRecipe> recipes = new Seq<>();

    public ls(String name) {
        super(name);
        configurable = true;
        saveConfig = true;
        sync = true;
        hasLiquids = true; // 确保启用液体存储
        // 允许接受物品和液体输入
        acceptsItems = true;
    }
    public void init() {
        super.init();
        // 为所有配方的输入物品和液体设置过滤
        recipes.each(recipe -> {
            if (recipe.inputItems != null) {
                for (ItemStack stack : recipe.inputItems) {
                    itemFilter[stack.item.id] = true; // 允许该物品输入
                }
            }
            if (recipe.inputLiquids != null) {
                for (LiquidStack stack : recipe.inputLiquids) {
                    liquidFilter[stack.liquid.id] = true; // 允许该液体输入
                }
            }
        });
    }

    public void addRecipe(String name,
                          ItemStack[] inputItems,
                          LiquidStack[] inputLiquids,
                          ItemStack[] outputItems,
                          LiquidStack[] outputLiquids,
                          float craftTime,
                          float powerUse) {
        recipes.add(new NewRecipe(name, inputItems, inputLiquids, outputItems, outputLiquids, craftTime, powerUse));
    }

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

    public static class NewRecipe {
        public String name;
        public ItemStack[] inputItems;
        public LiquidStack[] inputLiquids;
        public ItemStack[] outputItems;
        public LiquidStack[] outputLiquids;
        public float craftTime;
        public float powerUse;

        public NewRecipe(String name, ItemStack[] inputItems, LiquidStack[] inputLiquids,
                         ItemStack[] outputItems, LiquidStack[] outputLiquids,
                         float craftTime, float powerUse) {
            this.name = name;
            this.inputItems = inputItems;
            this.inputLiquids = inputLiquids;
            this.outputItems = outputItems;
            this.outputLiquids = outputLiquids;
            this.craftTime = craftTime;
            this.powerUse = powerUse;
        }
    }

    public class CustomCrafterBuild extends GenericCrafterBuild {
        public int currentRecipe = 0;
        // 1. 定义控制变量（新增这行）
        private boolean needsRecipeUpdate = false;

        @Override
        public void updateTile() {
            // 只在需要时调用applyCurrentRecipe()，例如配方切换时才调用，而非每次更新都调用
            // 若必须每次更新都检查配方，需添加条件避免重复执行
            if (needsRecipeUpdate) { // 用一个布尔变量控制，配方切换时设为true
                applyCurrentRecipe();
                needsRecipeUpdate = false;
            }
            super.updateTile();
        }

        private void applyCurrentRecipe() {
            if (recipes.isEmpty()) return;

            currentRecipe = Mathf.clamp(currentRecipe, 0, recipes.size - 1);
            NewRecipe recipe = recipes.get(currentRecipe);

            craftTime = recipe.craftTime;
            // 移除单物品输出设置（避免父类逻辑冲突）

            // 1. 计算新消耗的总数量（物品组+液体+电力）
            int newSize = 0;
            if (recipe.inputItems != null && recipe.inputItems.length > 0) newSize += 1; // 物品组算1项
            if (recipe.inputLiquids != null) newSize += recipe.inputLiquids.length;
            newSize += 1; // 电力消耗

            // 2. 创建新的消耗数组（替代clear()）
            Consume[] newConsumers = new Consume[newSize];
            int index = 0;

            // 3. 添加物品消耗（使用ConsumeItems，支持多个物品）
            if (recipe.inputItems != null && recipe.inputItems.length > 0) {
                newConsumers[index++] = new ConsumeItems(recipe.inputItems){{
                    update = true;
                }};
            }

            // 4. 添加液体消耗
            if (recipe.inputLiquids != null) {
                for (LiquidStack stack : recipe.inputLiquids) {
                    newConsumers[index++] = new ConsumeLiquid(stack.liquid, stack.amount){{
                        update = true;
                        //perSecond = true;
                    }};
                }
            }

            // 5. 添加电力消耗
            newConsumers[index] = new ConsumePower(recipe.powerUse,45.4f,false){{

                update = true;
            }};

            // 6. 用新数组替换旧消耗数组
            block.consumers = newConsumers;

            // 刷新消耗状态
            //updateTile();
            //progress = 0f;
        }




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

                                    if (recipe.outputItems != null && recipe.outputItems.length > 0) {
                                        b.table(outputs -> {
                                            outputs.add("物品输出: ");
                                            for (ItemStack stack : recipe.outputItems) {
                                                outputs.image(stack.item.uiIcon).size(20f).pad(2f);
                                                outputs.add(stack.amount + " ").padRight(4f);
                                            }
                                        }).left().row();
                                    }

                                    // 保留流体输出显示（表明会产生流体）
                                    if (recipe.outputLiquids != null && recipe.outputLiquids.length > 0) {
                                        b.table(outputs -> {
                                            outputs.add("流体存储: "); // 修改标签为"流体存储"更准确
                                            for (LiquidStack stack : recipe.outputLiquids) {
                                                outputs.image(stack.liquid.uiIcon).size(20f).pad(2f);
                                                outputs.add(stack.amount + "/t ").padRight(4f);
                                            }
                                        }).left().row();
                                    }
                                });
                            }, Styles.flatToggleMenut, () -> {
                                currentRecipe = index;
                                needsRecipeUpdate = true; // 配方切换时设置标志
                                configure((byte) index);
                            }).update(btn -> btn.setChecked(index == currentRecipe))
                            .pad(2f).fillX().row();
                }
            }).fillX();
        }

        @Override
        public void displayBars(Table table) {
            super.displayBars(table);
            // 保留流体存储显示，方便查看当前存储量
            if (hasLiquids && liquids != null) {
                // 使用LiquidModule的each()方法遍历所有非零流体
                liquids.each((liquid, amount) -> {
                    if (amount > 0.001f) {
                        table.add(new Bar(
                                () -> liquid.localizedName,
                                () -> liquid.color,
                                () -> amount / liquidCapacity
                        )).growX().row();
                    }
                });
            }
        }
    }
}