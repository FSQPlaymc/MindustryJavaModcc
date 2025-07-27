package api;
import arc.struct.Seq;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeLiquid;

public class c extends GenericCrafter {
    public Seq<NewRecipe> recipes = new Seq<>();
    // 默认配方索引
    public int defaultRecipe = 0;
    public c(String name) {
        super(name);
        sync = true; // 添加同步支持
        configurable = true;    // 允许配置，这样玩家点击方块时会出现配置界面
        saveConfig = true;
    }
    public static class Recipe{
        public String name;
        public ItemStack[] input;
        public ItemStack[][] output;
        public float craftTime;
        public float powerUse;
        public LiquidStack liquid;
        public Recipe(String name, ItemStack[] input, ItemStack[] output, float craftTime, float powerUse) {
            this.name = name;
            this.input = input;
            this.output = new ItemStack[][]{output};
            this.craftTime = craftTime;
            this.powerUse = powerUse;
        }
        // 从物品堆栈创建
        public Recipe(String name, ItemStack input, ItemStack output, float craftTime, float powerUse) {
            this(name, new ItemStack[]{input}, new ItemStack[]{output}, craftTime, powerUse);
        }
    }


public class cs extends GenericCrafter.GenericCrafterBuild {
    public void GenericCrafterBuild() {
    }
    // 当前选择的配方索引
    public int currentRecipe = 0;
    public void updateTile() {
        applyCurrentRecipe();
        super.updateTile();

    }

    public void applyCurrentRecipe() {
        if (recipes.size > 0) {


            // 设置消耗和产出
            //consume.clear();
consume();


            // 设置电力消耗


            }
        }
    }
}
