import java.util.*;

public class DynamicProgramming {
    public static void main(String[] args) {
        // 测试最长公共子序列
        String X = "ABCBDAB";
        String Y = "BDCABA";
        System.out.println("最长公共子序列:");
        System.out.println("序列1: " + X);
        System.out.println("序列2: " + Y);
        String lcs = lcs(X, Y);
        System.out.println("最长公共子序列: " + lcs);
        System.out.println("长度: " + lcs.length());
        System.out.println();

        // 测试矩阵链乘法
        int[] dimensions = {50, 20, 25, 10, 30};
        System.out.println("矩阵链乘法:");
        System.out.println("矩阵维度: " + Arrays.toString(dimensions));
        OptimizedMatrixChain solver = new OptimizedMatrixChain(dimensions);
        int minMultiplications = solver.solve();
        System.out.println("最小标量乘法次数: " + minMultiplications);
        System.out.println("最优乘法顺序: ");
        solver.printOptimalParenthesization(0, dimensions.length - 2);
        System.out.println();

        // 测试最短路径问题
        int[][] graph = {
                {0, 3, 8, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {3, 0, 2, 6, Integer.MAX_VALUE},
                {8, 2, 0, 5, 3},
                {Integer.MAX_VALUE, 6, 5, 0, 9},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 9, 0}
        };
        System.out.println("最短路径问题:");
        FloydSolver floyd = new FloydSolver(graph);
        System.out.println("所有节点间最短路径:");
        floyd.printAllPaths();
    }

    // 最长公共子序列
    public static String lcs(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        // 空间优化：使用一维数组
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int[] newDp = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    newDp[j] = dp[j - 1] + 1;
                } else {
                    newDp[j] = Math.max(dp[j], newDp[j - 1]);
                }
            }
            dp = newDp;
        }

        // 回溯构造LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs.append(X.charAt(i - 1));
                i--;
                j--;
            } else if (dp[j] > dp[j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }
}

// 矩阵链乘法优化实现
class OptimizedMatrixChain {
    private int[] dimensions;
    private int[][] m;
    private int[][] s;

    public OptimizedMatrixChain(int[] dimensions) {
        this.dimensions = dimensions;
        int n = dimensions.length - 1;
        m = new int[n][n];
        s = new int[n][n];
    }

    public int solve() {
        int n = dimensions.length - 1;

        // 初始化对角线
        for (int i = 0; i < n; i++) {
            m[i][i] = 0;
        }

        // 填充表
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i <= n - L; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] +
                            dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        return m[0][n - 1];
    }

    public void printOptimalParenthesization(int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParenthesization(i, s[i][j]);
            printOptimalParenthesization(s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}

// 最短路径问题（Floyd算法）
class FloydSolver {
    private int[][] dist;
    private int n;

    public FloydSolver(int[][] graph) {
        n = graph.length;
        dist = new int[n][n];

        // 初始化距离矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
            }
        }
    }

    public void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    public void printAllPaths() {
        floyd();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    System.out.println(i + " -> " + j + ": " + dist[i][j]);
                }
            }
        }
    }
}
