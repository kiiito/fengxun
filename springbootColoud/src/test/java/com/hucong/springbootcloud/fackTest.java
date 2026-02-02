package com.hucong.springbootcloud;

import java.math.BigInteger;
import java.util.*;

public class fackTest {
}
class LL1Parser {
    private static final Map<String, List<String[]>> grammar = new HashMap<>();
    private static final Map<String, Map<String, String>> parsingTable = new HashMap<>();
    private static final Set<String> nonTerminals = new HashSet<>();
    private static final Set<String> terminals = new HashSet<>(Arrays.asList("i", "+", "*", "(", ")", "#"));

//    static {
//        // 初始化文法：E→TE', E'→+TE'|ε, T→FT', T'→*FT'|ε, F→(E)|i
//        grammar.put("E", Arrays.asList(new String[]{"T", "E'"}));
//        grammar.put("E'", Arrays.asList(new String[]{"+", "T", "E'"}, new String[]{"ε"}));
//        grammar.put("T", Arrays.asList(new String[]{"F", "T'"}));
//        grammar.put("T'", Arrays.asList(new String[]{"*", "F", "T'"}, new String[]{"ε"}));
//        grammar.put("F", Arrays.asList(new String[]{"(", "E", ")"}, new String[]{"i"}));
//
//        // 初始化非终结符
//        nonTerminals.addAll(grammar.keySet());
//
//        // 构建预测分析表
//        initParsingTable();
//    }
// 修正文法定义部分
static {
    // 初始化文法：E→TE', E'→+TE'|ε, T→FT', T'→*FT'|ε, F→(E)|i
    grammar.put("E", Arrays.asList(new String[][]{{"T", "E'"}}));
    grammar.put("E'", Arrays.asList(new String[][]{{"+", "T", "E'"}, {"ε"}}));
    grammar.put("T", Arrays.asList(new String[][]{{"F", "T'"}}));
    grammar.put("T'", Arrays.asList(new String[][]{{"*", "F", "T'"}, {"ε"}}));
    grammar.put("F", Arrays.asList(new String[][]{{"(", "E", ")"}, {"i"}}));

    // 初始化非终结符
    nonTerminals.addAll(grammar.keySet());

    // 构建预测分析表
    initParsingTable();
}


    private static void initParsingTable() {
        // 初始化分析表结构
        for (String nonTerminal : nonTerminals) {
            parsingTable.put(nonTerminal, new HashMap<>());
            for (String terminal : terminals) {
                parsingTable.get(nonTerminal).put(terminal, "");
            }
        }

        // 填充预测分析表
        parsingTable.get("E").put("i", "E→TE'");
        parsingTable.get("E").put("(", "E→TE'");

        parsingTable.get("E'").put("+", "E'→+TE'");
        parsingTable.get("E'").put(")", "E'→ε");
        parsingTable.get("E'").put("#", "E'→ε");

        parsingTable.get("T").put("i", "T→FT'");
        parsingTable.get("T").put("(", "T→FT'");

        parsingTable.get("T'").put("+", "T'→ε");
        parsingTable.get("T'").put("*", "T'→*FT'");
        parsingTable.get("T'").put(")", "T'→ε");
        parsingTable.get("T'").put("#", "T'→ε");

        parsingTable.get("F").put("i", "F→i");
        parsingTable.get("F").put("(", "F→(E)");
    }

    public static void parse(String input) {
        System.out.printf("%-15s %-15s %-15s\n", "分析栈", "剩余串", "产生式");
        System.out.println("----------------------------------------");

        Stack<String> stack = new Stack<>();
        stack.push("#");
        stack.push("E");

        input = input + "#";
        int index = 0;

        while (!stack.isEmpty()) {
            String stackStr = getStackString(stack);
            String remainingStr = input.substring(index);

            String top = stack.peek();
            String currentInput = String.valueOf(input.charAt(index));

            if (top.equals(currentInput)) {
                // 匹配终结符
                if (top.equals("#")) {
                    System.out.printf("%-15s %-15s %-15s\n", stackStr, remainingStr, "接受");
                    break;
                } else {
                    System.out.printf("%-15s %-15s %-15s\n", stackStr, remainingStr, top + "匹配");
                    stack.pop();
                    index++;
                }
            } else if (nonTerminals.contains(top)) {
                // 非终结符，查表
                String production = parsingTable.get(top).get(currentInput);
                if (production.isEmpty()) {
                    System.out.println("错误：无法找到对应的产生式");
                    return;
                }

                System.out.printf("%-15s %-15s %-15s\n", stackStr, remainingStr, production);

                stack.pop();
                if (!production.contains("ε")) {
                    // 将产生式右部逆序入栈
                    String[] rightParts = production.split("→")[1].split("");
                    for (int i = rightParts.length - 1; i >= 0; i--) {
                        if (!rightParts[i].isEmpty()) {
                            stack.push(rightParts[i]);
                        }
                    }
                }
            } else {
                System.out.println("错误：栈顶符号和输入符号不匹配");
                return;
            }
        }
    }

    private static String getStackString(Stack<String> stack) {
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append(s);
        }
        return sb.reverse().toString();
    }

    public static void displayParsingTable() {
        System.out.println("预测分析表:");
        System.out.print("     ");
        for (String terminal : terminals) {
            System.out.printf("%-10s", terminal);
        }
        System.out.println();

        for (String nonTerminal : nonTerminals) {
            System.out.printf("%-5s", nonTerminal);
            for (String terminal : terminals) {
                String production = parsingTable.get(nonTerminal).get(terminal);
                System.out.printf("%-10s", production.isEmpty() ? "" : production);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== LL(1)文法预测分析表法 ===");
        displayParsingTable();
        System.out.println("\n分析过程:");
        parse("i+i*i");
    }

    class Factorial {
        public static BigInteger factorial(int n) {
            if (n < 0) {
                throw new IllegalArgumentException("n must be non-negative");
            }
            if (n == 0 || n == 1) {
                return BigInteger.ONE; // 0! = 1, 1! = 1
            }
            // 递归调用：n! = n * (n-1)!
            return BigInteger.valueOf(n).multiply(factorial(n - 1));
        }
    }

    class PellSequence {
        public static BigInteger pell(int k) {
            // 基准情况
            if (k == 1) {
                return BigInteger.ONE;
            }
            if (k == 2) {
                return BigInteger.valueOf(2);
            }
            return pell(k - 1).multiply(BigInteger.valueOf(2)).add(pell(k - 2));
        }

        public static BigInteger pellIterative(int k) {
            if (k == 1) return BigInteger.ONE;
            if (k == 2) return BigInteger.valueOf(2);

            BigInteger a = BigInteger.ONE;      // aₙ₋₂
            BigInteger b = BigInteger.valueOf(2); // aₙ₋₁
            BigInteger c = BigInteger.ZERO;     // aₙ

            for (int i = 3; i <= k; i++) {
                c = b.multiply(BigInteger.valueOf(2)).add(a);
                a = b;
                b = c;
            }
            return c;
        }

}

    public class PowerOfTwo {
        public static boolean isPowerOfTwoRecursive(int n) {
            if (n <= 0) {
                return false;
            }
            if (n == 1) {
                return true; // 2^0 = 1
            }
            if (n % 2 != 0) {
                return false; // 奇数且不是1，肯定不是2的幂
            }
            // 递归检查n/2
            return isPowerOfTwoRecursive(n / 2);
        }
        public static boolean isPowerOfTwoTailRecursive(int n) {
            if (n <= 0) {
                return false;
            }
            return isPowerOfTwoHelper(n);
        }
        private static boolean isPowerOfTwoHelper(int n) {
            if (n == 1) {
                return true;
            }
            if (n % 2 != 0) {
                return false;
            }
            return isPowerOfTwoHelper(n / 2);
        }
    }

}
