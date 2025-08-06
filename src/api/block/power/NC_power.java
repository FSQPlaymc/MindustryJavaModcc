package api.block.power;

import arc.util.Nullable;
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
