package com.hucong.springbootcloud.domain;

public class stairs {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int a = 1;
        int b = 2;
        for (int i = 3; i <= n; i++) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }
    // 测试代码
    public static void main(String[] args) {
        stairs solution = new stairs();
        System.out.println(solution.climbStairs(2));
        System.out.println(solution.climbStairs(3));
        System.out.println(solution.climbStairs(4));
    }
}
