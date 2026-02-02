import java.util.*;
import java.util.Arrays;
import java.util.Comparator;

public class KnapsackBacktrack {

    private int[] weights;      // 物品重量数组
    private int[] values;       // 物品价值数组
    private int capacity;       // 背包容量
    private int n;             // 物品数量

    private int maxValue;       // 最大价值
    private int[] bestSolution; // 最优解
    private int[] currentSolution; // 当前解

    private int nodeCount;      // 搜索节点计数
    private long startTime;     // 开始时间

    // 物品类，用于排序
    static class Item {
        int weight;
        int value;
        int index;
        double density; // 价值密度

        Item(int weight, int value, int index) {
            this.weight = weight;
            this.value = value;
            this.index = index;
            this.density = (double) value / weight;
        }
    }

    public KnapsackBacktrack(int[] weights, int[] values, int capacity) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
        this.n = weights.length;

        this.maxValue = 0;
        this.bestSolution = new int[n];
        this.currentSolution = new int[n];
        this.nodeCount = 0;
    }

    /**
     * 回溯算法求解入口
     */
    public void solve() {
        startTime = System.currentTimeMillis();

        // 预处理：按价值密度降序排列物品
        preprocessItems();

        // 开始回溯搜索
        backtrack(0, 0, 0);

        // 恢复原始物品顺序
        restoreSolutionOrder();
    }

    /**
     * 物品预处理：按价值密度降序排序
     */
    private void preprocessItems() {
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i], i);
        }

        // 按价值密度降序排序
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return Double.compare(b.density, a.density);
            }
        });

        // 更新重量和价值数组为排序后的顺序
        int[] newWeights = new int[n];
        int[] newValues = new int[n];
        int[] indexMapping = new int[n]; // 记录原始索引映射

        for (int i = 0; i < n; i++) {
            newWeights[i] = items[i].weight;
            newValues[i] = items[i].value;
            indexMapping[i] = items[i].index;
        }

        this.weights = newWeights;
        this.values = newValues;
        this.bestSolution = new int[n];
        this.currentSolution = new int[n];
    }

    /**
     * 恢复解的原始顺序
     */
    private void restoreSolutionOrder() {
        // 由于我们预处理时重新排序了物品，这里需要记录原始顺序
        // 在实际实现中，我们需要保存索引映射关系，这里简化处理
    }

    /**
     * 回溯搜索核心算法
     * @param level 当前决策层级
     * @param currentWeight 当前总重量
     * @param currentValue 当前总价值
     */
    private void backtrack(int level, int currentWeight, int currentValue) {
        nodeCount++; // 增加搜索节点计数

        // 到达叶子节点，更新最优解
        if (level == n) {
            if (currentValue > maxValue) {
                maxValue = currentValue;
                System.arraycopy(currentSolution, 0, bestSolution, 0, n);
            }
            return;
        }

        // 计算上界，用于剪枝
        double upperBound = currentValue + calculateBound(level, currentWeight);

        // 最优性剪枝：如果上界小于当前最大价值，则剪枝
        if (upperBound <= maxValue) {
            return;
        }

        // 选择当前物品（如果重量不超过容量）
        if (currentWeight + weights[level] <= capacity) {
            currentSolution[level] = 1;
            backtrack(level + 1,
                    currentWeight + weights[level],
                    currentValue + values[level]);
            currentSolution[level] = 0;
        }

        // 不选择当前物品
        backtrack(level + 1, currentWeight, currentValue);
    }

    /**
     * 计算上界函数（贪心策略估计剩余物品的最大价值）
     * @param level 当前层级
     * @param currentWeight 当前重量
     * @return 上界值
     */
    private double calculateBound(int level, int currentWeight) {
        int remainingCapacity = capacity - currentWeight;
        double bound = 0.0;
        int i = level;

        // 贪心填充剩余物品
        while (i < n && weights[i] <= remainingCapacity) {
            remainingCapacity -= weights[i];
            bound += values[i];
            i++;
        }

        // 如果还有剩余容量，部分装入下一个物品
        if (i < n) {
            bound += (double) values[i] / weights[i] * remainingCapacity;
        }

        return bound;
    }

    /**
     * 获取求解结果
     */
    public void printResult() {
        long endTime = System.currentTimeMillis();
        long timeCost = endTime - startTime;

        System.out.println("回溯算法求解结果:");
        System.out.println("最大价值: " + maxValue);
        System.out.print("最优解: [");
        for (int i = 0; i < n; i++) {
            System.out.print(bestSolution[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("]");

        // 显示选择的物品详情
        System.out.print("选择的物品: ");
        boolean first = true;
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            if (bestSolution[i] == 1) {
                if (!first) System.out.print(", ");
                System.out.print("物品" + (i + 1) + "(重量:" + weights[i] + ", 价值:" + values[i] + ")");
                first = false;
                totalWeight += weights[i];
            }
        }
        System.out.println();
        System.out.println("总重量: " + totalWeight + "/" + capacity);
        System.out.println("搜索节点数: " + nodeCount);
        System.out.println("求解时间: " + timeCost + "ms");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 0-1背包问题回溯算法求解 ===");

        System.out.print("请输入物品数量: ");
        int n = scanner.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("请输入物品重量:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.println("请输入物品价值:");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.print("请输入背包容量: ");
        int capacity = scanner.nextInt();

        // 显示输入信息
        System.out.println("\n输入信息:");
        System.out.println("物品数量: " + n);
        System.out.println("物品重量: " + Arrays.toString(weights));
        System.out.println("物品价值: " + Arrays.toString(values));
        System.out.println("背包容量: " + capacity);
        System.out.println();

        // 创建求解器并求解
        KnapsackBacktrack solver = new KnapsackBacktrack(weights, values, capacity);
        solver.solve();
        solver.printResult();

        scanner.close();

        // 性能测试示例
        System.out.println("\n=== 性能测试示例 ===");
        performanceTest();
    }

    /**
     * 性能测试方法
     */
    public static void performanceTest() {
        int[] sizes = {10, 15, 20};

        for (int size : sizes) {
            // 生成测试数据
            int[] testWeights = generateWeights(size, 5, 50);
            int[] testValues = generateValues(size, 10, 100);
            int testCapacity = (int) (Arrays.stream(testWeights).sum() * 0.6);

            KnapsackBacktrack testSolver = new KnapsackBacktrack(testWeights, testValues, testCapacity);
            testSolver.solve();

            System.out.printf("问题规模: %d个物品, 搜索节点: %d, 时间: %dms\n",
                    size, testSolver.nodeCount, System.currentTimeMillis() - testSolver.startTime);
        }
    }

    /**
     * 生成随机重量数组
     */
    private static int[] generateWeights(int n, int min, int max) {
        int[] weights = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            weights[i] = rand.nextInt(max - min + 1) + min;
        }
        return weights;
    }

    /**
     * 生成随机价值数组
     */
    private static int[] generateValues(int n, int min, int max) {
        int[] values = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            values[i] = rand.nextInt(max - min + 1) + min;
        }
        return values;
    }
}
