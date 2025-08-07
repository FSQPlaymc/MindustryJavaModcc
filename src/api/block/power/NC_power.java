package api.block.power;

import arc.util.Nullable;
import content.GG_Block.GG_factory;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.power.PowerGenerator;

public class NC_power extends NuclearReactor {
    public boolean structureComplete = false;
    public int progress = 0;
    public final int MAX_PROGRESS = 100;
    public NC_power(String name) {
        super(name);
        update = true;
        solid = true;
        configurable = true;
    }
    private int factoryX = 0;
    private int factoryY = 0;
    private int FL = 0;
    private int FS = 0;
    private int[][] asdf;
    private int checkX;
    private int checkY;
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
                    for (int i = 2; i <= 64; i=i+2) {
                         checkX = tileX - i; // 左侧第i个位置
                        Building neighbor = Vars.world.build(checkX, tileY);

                        // 检查方块是否存在且为GG_factory.cs
                        if (neighbor != null && neighbor.block == GG_factory.cs) {
                            factoryX=factoryX-2;
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
                    for (int i = 2; i <= 64; i=i+2) {
                         checkX = tileX + i; // 左侧第i个位置
                        Building neighbor = Vars.world.build(checkX, tileY);

                        // 检查方块是否存在且为GG_factory.cs
                        if (neighbor != null && neighbor.block == GG_factory.cs) {
                            factoryX=factoryX+2;
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
                    for (int i = 2; i <= 64; i=i+2) {
                         checkY = tileY - i; // 下侧第i个位置
                        Building neighbor = Vars.world.build(tileX, checkY);

                        // 检查方块是否存在且为GG_factory.cs
                        if (neighbor != null && neighbor.block == GG_factory.cs) {
                            factoryY = factoryY -2;
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
                    for (int i = 2; i <= 64; i=i+2) {
                         checkY = tileY + i; // 上侧第i个位置
                        Building neighbor = Vars.world.build(tileX, checkY);
                        // 检查方块是否存在且为GG_factory.cs
                        if (neighbor != null && neighbor.block == GG_factory.cs) {
                            factoryY = factoryY +2;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_factory.GG_factoryBuild factory = (GG_factory.GG_factoryBuild) neighbor;
                            // factory.doSomething();
                        } else {
                            // 遇到非工厂方块或空位置，停止检测
                            break;
                        }
                    }
                }
                if (factoryX<0){FL= factoryX*-1;}
                if (factoryY <0){FS= factoryY *-1;}
                asdf =new int[FL][FS];
                int L=FL;
                int S=FS;
                int X=checkX;
                int Y=checkY;
                for (;;){
                    Building neighboru = Vars.world.build(X, Y);
                    if (neighboru != null && neighboru.block == GG_factory.cs){
                        asdf[L][S]=90;
                    }else if (neighboru != null && neighboru.block == GG_factory.SL){
                        asdf[L][S]=1;
                    }else if (neighboru != null && neighboru.block == GG_factory.fanying){
                        asdf[L][S]=80;
                    }else {asdf[L][S]=-1;}
                    L=L-2;
                    if (L<0){L=FL;
                        S=S-2;if (S<0){
                            break;
                        }
                    }
                    X=X-2;
                    if (X<tileX){
                        X=checkX;
                        Y=Y-2;if (Y<0){
                            break;
                        }
                    }
                }


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
