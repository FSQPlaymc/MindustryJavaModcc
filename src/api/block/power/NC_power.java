package api.block.power;

import arc.util.Nullable;
import content.GG_Block.GG_factory;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.PowerGenerator;

public class NC_power extends ConsumeGenerator {
    public boolean structureComplete = false;
    public int progress = 0;
    public final int MAX_PROGRESS = 100;
    public NC_power(String name) {
        super(name);
        update = true;
        solid = true;
        configurable = true;
    }
    static int factoryCount = 0; // 计数器：符合要求的方块数量
    static int factoryS =0;
    static int FL;
    static int FS;
    public class NC_powerBuid extends PowerGenerator.GeneratorBuild{
            public void update() {
                // 获取建筑所在的主 Tile 坐标
                int tileX = tile.x;  // 网格坐标 X
                int tileY = tile.y;  // 网格坐标 Y

                // 获取建筑中心的世界坐标（像素）
                float worldX = x;    // 世界坐标 X (像素)
                float worldY = y;    // 世界坐标 Y (像素)

                // 获取当前 Tile 的世界坐标（左上角）
                float tileWorldX = tile.worldx();
                float tileWorldY = tile.worldy();

                Building neighborL = Vars.world.build(tileX - 1, tileY); // 左
                Building neighborS = Vars.world.build(tileX, tileY + 1); // 上
                if (neighborL != null && neighborL.block == GG_factory.cs) {
                    // 邻居是 GG_factory.cs 方块
                    // 循环检测左侧N个方块
                    for (int i = 1; i <= 64; i++) {
                        int checkX = tileX - i; // 左侧第i个位置
                        Building neighbor = Vars.world.build(checkX, tileY);

                        // 检查方块是否存在且为GG_factory.cs
                        if (neighbor != null && neighbor.block == GG_factory.cs) {
                            factoryCount--;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_factory.GG_factoryBuild factory = (GG_factory.GG_factoryBuild) neighbor;
                            // factory.doSomething();
                        } else {
                            // 遇到非工厂方块或空位置，停止检测
                            break;
                        }
                    }
                }else {
                    // 循环检测右侧N个方块
                    for (int i = 1; i <= 64; i++) {
                        int checkX = tileX + i; // 左侧第i个位置
                        Building neighbor = Vars.world.build(checkX, tileY);

                        // 检查方块是否存在且为GG_factory.cs
                        if (neighbor != null && neighbor.block == GG_factory.cs) {
                            factoryCount++;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_factory.GG_factoryBuild factory = (GG_factory.GG_factoryBuild) neighbor;
                            // factory.doSomething();
                        } else {
                            // 遇到非工厂方块或空位置，停止检测
                            break;
                        }
                    }
                }if (neighborS != null && neighborS.block == GG_factory.cs) {
                    // 邻居是 GG_factory.cs 方块
                    // 循环检测下侧N个方块
                    for (int i = 1; i <= 64; i++) {
                        int checkY = tileY - i; // 下侧第i个位置
                        Building neighbor = Vars.world.build(tileX, checkY);

                        // 检查方块是否存在且为GG_factory.cs
                        if (neighbor != null && neighbor.block == GG_factory.cs) {
                            factoryS--;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_factory.GG_factoryBuild factory = (GG_factory.GG_factoryBuild) neighbor;
                            // factory.doSomething();
                        } else {
                            // 遇到非工厂方块或空位置，停止检测
                            break;
                        }
                    }
                }else {
                    // 循环检测上侧N个方块
                    for (int i = 1; i <= 64; i++) {
                        int checkY = tileY + i; // 上侧第i个位置
                        Building neighbor = Vars.world.build(tileX, checkY);
                        // 检查方块是否存在且为GG_factory.cs
                        if (neighbor != null && neighbor.block == GG_factory.cs) {
                            factoryS++;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_factory.GG_factoryBuild factory = (GG_factory.GG_factoryBuild) neighbor;
                            // factory.doSomething();
                        } else {
                            // 遇到非工厂方块或空位置，停止检测
                            break;
                        }
                    }
                }
                if (factoryCount<0){FL= factoryCount*-1;}
                if (factoryS<0){FS= factoryS*-1;}

        }
        @Nullable
        public Building nearbyBuild(int rotation) {

                    // 获取建筑所在的主 Tile 坐标
            int tileX = tile.x;  // 网格坐标 X
            int tileY = tile.y;  // 网格坐标 Y

            // 获取建筑中心的世界坐标（像素）
            float worldX = x;    // 世界坐标 X (像素)
            float worldY = y;    // 世界坐标 Y (像素)

            // 获取当前 Tile 的世界坐标（左上角）
            float tileWorldX = tile.worldx();
            float tileWorldY = tile.worldy();

            return switch (rotation) {
                // 获取当前 Tile 的世界坐标（左上角）
                case 0 -> Vars.world.build(tileX + 1, tileY); // 右
                case 1 -> Vars.world.build(tileX, tileY + 1); // 上
                case 2 -> Vars.world.build(tileX - 1, tileY); // 左
                case 3 -> Vars.world.build(tileX, tileY - 1); // 下
                default -> null;
            };
        }
    }
}
