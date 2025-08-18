package api.block.power;

import arc.Events;
import arc.math.Mathf;
import arc.util.Nullable;
import content.GG_Block.GG_walls;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.power.PowerGenerator;

public class NC_power extends NuclearReactor {
    public NC_power(String name) {
        super(name);
        update = true;
        solid = true;
        configurable = true;
    }
    public Item fuelItem = Items.thorium;//使用的燃料
    public float heating = 0.01f;//（每帧产热速率）
    // 定义计时器ID（可自定义，只要唯一即可）
    private static final int UPDATE_TIMER = 0;
    // 定义调用间隔（单位： ticks，60ticks = 1秒）
    private static final float UPDATE_INTERVAL = 120f; // 1秒调用一次
    private int factoryX = 0;
    private int factoryY = 0;
    private int FL = 0;
    private int FS = 0;
    private int[][] asdf;
    private int checkX;
    private int DWS;//单元数
    private float SQQ;//冷却量
    private int checkY;
    private float SDQ;
    public void setBars(){
        super.setBars();
        addBar("heats"+SDQ, (NuclearReactorBuild entitys)
                -> new Bar("bar.heats", Pal.lightOrange, () -> entitys.heat)
        );
    }
    public class NC_powerBuid extends NuclearReactorBuild{
        public float SQl;
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
                if (neighborL != null && neighborL.block == GG_walls.cs) {
                    // 邻居是 GG_walls.cs 方块
                    // 循环检测左侧N个方块
                    for (int i = 2; i <= 64; i=i+2) {
                         checkX = tileX - i; // 左侧第i个位置
                        Building neighbor = Vars.world.build(checkX, tileY);

                        // 检查方块是否存在且为GG_walls.cs
                        if (neighbor != null &&  (neighbor.block == GG_walls.cs || neighbor.block == GG_walls.glass)) {
                            factoryX=factoryX-1;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_walls.GG_wallsBuild factory = (GG_walls.GG_wallsBuild) neighbor;
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

                        // 检查方块是否存在且为GG_walls.cs
                        if (neighbor != null &&  (neighbor.block == GG_walls.cs || neighbor.block == GG_walls.glass)) {
                            factoryX=factoryX+1;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_walls.GG_wallsBuild factory = (GG_walls.GG_wallsBuild) neighbor;
                            // factory.doSomething();
                        } else {
                            // 遇到非工厂方块或空位置，停止检测
                            break;
                        }
                    }
                }if (neighborS != null && neighborS.block == GG_walls.cs) {
                    // 邻居是 GG_walls.cs 方块
                    // 循环检测下侧N个方块
                    for (int i = 2; i <= 64; i=i+2) {
                         checkY = tileY - i; // 下侧第i个位置
                        Building neighbor = Vars.world.build(tileX, checkY);

                        // 检查方块是否存在且为GG_walls.cs
                        if (neighbor != null &&  (neighbor.block == GG_walls.cs || neighbor.block == GG_walls.glass)) {
                            factoryY = factoryY -1;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_walls.GG_wallsBuild factory = (GG_walls.GG_wallsBuild) neighbor;
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
                        // 检查方块是否存在且为GG_walls.cs
                        if (neighbor != null &&  (neighbor.block == GG_walls.cs || neighbor.block == GG_walls.glass)) {
                            factoryY = factoryY +1;
                            // 在这里添加对每个检测到的工厂的操作
                            // 例如：GG_walls.GG_wallsBuild factory = (GG_walls.GG_wallsBuild) neighbor;
                            // factory.doSomething();
                        } else {
                            // 遇到非工厂方块或空位置，停止检测
                            break;
                        }
                    }
                }
                if (factoryX<0){FL= factoryX*-1;}else{FL=factoryX;}
                if (factoryY <0){FS= factoryY *-1;}else{FS=factoryY;}
                asdf =new int[FL][FS];
                int L=FL-1;
                int S=FS-1;
                int X=checkX;
                int Y=checkY;
                for (int i=1;i<256;i++){
                    Building neighboru = Vars.world.build(X, Y);
                    if (neighboru != null && (neighboru.block == GG_walls.cs || neighboru.block == GG_walls.glass)){
                        asdf[L][S]=90;
                    }else if (neighboru != null && neighboru.block == GG_walls.SL){
                        asdf[L][S]=1;
                        SQQ+=9F* Math.min(this.delta(), 4.0F);//测试用
                    }else if (neighboru != null && neighboru.block == GG_walls.fanying){
                        asdf[L][S]=80;
                        DWS++;
                    }else {asdf[L][S]=-1;}
                    L=L-1;
                    if (L<0){L=FL-1;
                        S=S-1;if (S<0){
                            break;
                        }
                    }
                    X=X-2;
                    if (X<tileX){
                        X=checkX;
                        Y=Y-2;
                    }
                }



            }

        public void updateTile(){
            // 计时器逻辑：每隔 UPDATE_INTERVAL 时间触发一次
            if (timer(UPDATE_TIMER, UPDATE_INTERVAL)) {
                // 定时调用 update() 方法
                update();
            }
            // 1. 获取当前燃料（钍）的数量，计算燃料满度（占总容量的比例）
            int fuel = this.items.get(NC_power.this.fuelItem);
            float fullness = (float)(1+((DWS-1)*0.8));
            this.productionEfficiency = 1.0f; // 发电效率与燃料满度挂钩
            SDQ= SQQ-fullness;
            // 2. 燃料燃烧逻辑：若有燃料且反应堆启用，则产生热量并消耗燃料
            if (fuel > 0 && this.enabled) {
                // 热量随燃料满度和时间增加（delta()是本帧耗时，限制最大4ms防止跳变）
                this.heat += fullness * NC_power.this.heating * Math.min(this.delta(), 4.0F);

                // 定时消耗燃料：当燃料计时器达到设定值（itemDuration / 时间缩放）时，消耗1单位燃料
                if (this.timer(NC_power.this.timerFuel, NC_power.this.itemDuration / this.timeScale)) {
                    this.consume(); // 内部会减少1单位fuelItem（钍）
                }
            } else {
                // 无燃料或未启用时，发电效率为0
                this.productionEfficiency = 0.0F;
            }
            heat-= SQQ;
            // 3. 冷却逻辑：若有冷却液，消耗冷却液并降低热量
            if (this.heat > 0.0F) {
                // 计算最大可使用的冷却剂量（不超过当前液体量，且不超过当前热量可冷却的量）
                float maxUsed = Math.min(this.liquids.currentAmount(), this.heat / NC_power.this.coolantPower);
                this.heat -= maxUsed * NC_power.this.coolantPower; // 热量降低 = 冷却液量 * 冷却效率
                this.liquids.remove(this.liquids.current(), maxUsed); // 消耗对应量的冷却液
            }

            // 4. 烟雾效果：当热量超过烟雾阈值时，随机产生烟雾
            if (this.heat > NC_power.this.smokeThreshold) {
                // 烟雾概率随热量升高而增加（heat越高，smoke值越大，概率越高）
                float smoke = 1.0F + (this.heat - NC_power.this.smokeThreshold) / (1.0F - NC_power.this.smokeThreshold);
                if (Mathf.chance((double)smoke / (double)20.0F * (double)this.delta())) {
                    // 在反应堆范围内随机位置产生烟雾效果
                    Fx.reactorsmoke.at(
                            this.x + Mathf.range((float)(NC_power.this.size * 8) / 2.0F),
                            this.y + Mathf.range((float)(NC_power.this.size * 8) / 2.0F)
                    );
                }
            }

            // 5. 热量限制：确保热量在0~1之间
            this.heat = Mathf.clamp(this.heat);

            // 6. 过热爆炸：当热量接近最大值（≥0.999）时，触发过热事件并销毁反应堆
            if (this.heat >= 0.999F) {
                 // 触发全局过热事件
                Events.fire(EventType.Trigger.thoriumReactorOverheat);
                kill(); // 销毁自身（会触发爆炸效果）
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
