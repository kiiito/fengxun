import java.util.Stack;

public class Solution {
    public int reverse(int x) {
        int reversed = 0;
        int sign = x < 0 ? -1 : 1;
        int num = Math.abs(x);

        while (num != 0) {
            int digit = num % 10;
            // 检查是否会导致溢出
            if (reversed > (Integer.MAX_VALUE - digit) / 10) {
                return 0; // 超出范围
            }
            reversed = reversed * 10 + digit;
            num /= 10;
        }

        return sign * reversed;
    }


    public String longestCommonPrefix(String[] strs) {
        // 边界情况：空数组
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以第一个字符串为基准
        String prefix = strs[0];

        // 从第二个字符串开始，逐个比较
        for (int i = 1; i < strs.length; i++) {
            // 缩短前缀，直到与当前字符串匹配
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 示例 1: x = 123
//        System.out.println("输入: 123");
//        System.out.println("输出: " + solution.reverse(123)); // 输出: 321
//
//        // 示例 2: x = -123
//        System.out.println("输入: -123");
//        System.out.println("输出: " + solution.reverse(-123)); // 输出: -321

//        System.out.println(s.isPalindrome(121));



        // 示例 1
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("输入: [" + String.join(", ", strs1) + "]");
        System.out.println("输出: \"" + solution.longestCommonPrefix(strs1) + "\""); // 输出: "fl"

        // 示例 2
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("输入: [" + String.join(", ", strs2) + "]");
        System.out.println("输出: \"" + solution.longestCommonPrefix(strs2) + "\""); // 输出: ""

        // 额外测试：单个字符串
        String[] strs3 = {"hello"};
        System.out.println("输入: [" + String.join(", ", strs3) + "]");
        System.out.println("输出: \"" + solution.longestCommonPrefix(strs3) + "\""); // 输出: "hello"
    }
}