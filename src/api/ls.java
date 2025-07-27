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

public class ls extends GenericCrafter {
    public Seq<NewRecipe> recipes = new Seq<>();

    public ls(String name) {
        super(name);
        configurable = true;
        saveConfig = true;
        sync = true;
        hasLiquids = true; // 确保启用液体存储
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

        @Override
        public void updateTile() {
            applyCurrentRecipe();
            super.updateTile();
        }

        private void applyCurrentRecipe() {
            if (recipes.isEmpty()) return;

            currentRecipe = Mathf.clamp(currentRecipe, 0, recipes.size - 1);
            NewRecipe recipe = recipes.get(currentRecipe);

            craftTime = recipe.craftTime;
            outputItem = recipe.outputItems.length > 0 ? recipe.outputItems[0] : null;
            //consumeItem(Items.a);

            if (recipe.inputItems != null) {
                for (ItemStack stack : recipe.inputItems) {
                    consumeItem(stack.item, stack.amount);
                }
            }

            if (recipe.inputLiquids != null) {
                for (LiquidStack stack : recipe.inputLiquids) {
                    consumeLiquid(stack.liquid, stack.amount);
                }
            }

            consumePower(recipe.powerUse);

        }

        @Override
        public void craft() {
            super.craft();
            NewRecipe recipe = recipes.get(currentRecipe);

            // 仅保留流体存储功能，移除主动输出逻辑
            if (recipe.outputLiquids != null && hasLiquids) {
                for (LiquidStack stack : recipe.outputLiquids) {
                    // 直接存储到自身液体容器，超过容量时会自动截断
                    liquids.add(stack.liquid, stack.amount);
                }
            }
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
                                applyCurrentRecipe();
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