package homework;

import java.util.*;

public class Demo_4_8 {
        private static final int PAGE_RANGE = 9; // 页面号范围1-9
        private static final int SEQUENCE_LENGTH = 16; // 访问序列长度
        private static final int FRAME_COUNT = 4; // 物理块数量

        public static void main(String[] args) {
            // 生成随机页面访问序列
            List<Integer> pageSequence = generatePageSequence();
            System.out.println("生成的页面访问序列: " + pageSequence);

            // 执行三种算法
            System.out.println("\n=== OPT算法 ===");
            simulateOPT(pageSequence);

            System.out.println("\n=== FIFO算法 ===");
            simulateFIFO(pageSequence);

            System.out.println("\n=== LRU算法 ===");
            simulateLRU(pageSequence);
        }

        // 生成随机页面访问序列
        private static List<Integer> generatePageSequence() {
            Random random = new Random();
            List<Integer> sequence = new ArrayList<>();
            for (int i = 0; i < SEQUENCE_LENGTH; i++) {
                sequence.add(random.nextInt(PAGE_RANGE) + 1);
            }
            return sequence;
        }

        // OPT算法模拟
        private static void simulateOPT(List<Integer> sequence) {
            List<Integer> frames = new ArrayList<>();
            int pageFaults = 0;
            List<String> operations = new ArrayList<>();

            for (int i = 0; i < sequence.size(); i++) {
                int page = sequence.get(i);
                if (!frames.contains(page)) {
                    pageFaults++;
                    if (frames.size() < FRAME_COUNT) {
                        frames.add(page);
                        operations.add("调入页面: " + page);
                    } else {
                        // 查找将来最长时间不被访问的页面
                        int farthest = -1, replaceIndex = 0;
                        for (int j = 0; j < frames.size(); j++) {
                            int nextUse = Integer.MAX_VALUE;
                            for (int k = i + 1; k < sequence.size(); k++) {
                                if (sequence.get(k) == frames.get(j)) {
                                    nextUse = k;
                                    break;
                                }
                            }
                            if (nextUse > farthest) {
                                farthest = nextUse;
                                replaceIndex = j;
                            }
                        }
                        int replacedPage = frames.get(replaceIndex);
                        frames.set(replaceIndex, page);
                        operations.add("替换页面: " + replacedPage + " -> " + page);
                    }
                }
                printFrameStatus(i + 1, page, frames);
            }
            printResults(operations, pageFaults);
        }

        // FIFO算法模拟
        private static void simulateFIFO(List<Integer> sequence) {
            Queue<Integer> frames = new LinkedList<>();
            Set<Integer> frameSet = new HashSet<>();
            int pageFaults = 0;
            List<String> operations = new ArrayList<>();

            for (int i = 0; i < sequence.size(); i++) {
                int page = sequence.get(i);
                if (!frameSet.contains(page)) {
                    pageFaults++;
                    if (frames.size() < FRAME_COUNT) {
                        frames.add(page);
                        frameSet.add(page);
                        operations.add("调入页面: " + page);
                    } else {
                        int replacedPage = frames.poll();
                        frameSet.remove(replacedPage);
                        frames.add(page);
                        frameSet.add(page);
                        operations.add("替换页面: " + replacedPage + " -> " + page);
                    }
                }
                printFrameStatus(i + 1, page, new ArrayList<>(frames));
            }
            printResults(operations, pageFaults);
        }

        // LRU算法模拟
        private static void simulateLRU(List<Integer> sequence) {
            LinkedHashMap<Integer, Integer> frames = new LinkedHashMap<Integer, Integer>(FRAME_COUNT, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > FRAME_COUNT;
                }
            };
            int pageFaults = 0;
            List<String> operations = new ArrayList<>();

            for (int i = 0; i < sequence.size(); i++) {
                int page = sequence.get(i);
                if (!frames.containsKey(page)) {
                    pageFaults++;
                    if (frames.size() < FRAME_COUNT) {
                        operations.add("调入页面: " + page);
                    } else {
                        int replacedPage = frames.keySet().iterator().next();
                        operations.add("替换页面: " + replacedPage + " -> " + page);
                    }
                    frames.put(page, i);
                } else {
                    // 更新访问时间
                    frames.put(page, i);
                }
                printFrameStatus(i + 1, page, new ArrayList<>(frames.keySet()));
            }
            printResults(operations, pageFaults);
        }

    // 打印当前帧状态
    private static void printFrameStatus(int step, int page, List<Integer> frames) {
        System.out.printf("步骤%2d: 访问页面%-2d | 内存状态: ", step, page);
        for (int i = 0; i < FRAME_COUNT; i++) {
            if (i < frames.size()) {
                System.out.printf("%-2d ", frames.get(i));
            } else {
                System.out.print("空 ");
            }
        }
        System.out.println();
    }

    // 打印最终结果
    private static void printResults(List<String> operations, int pageFaults) {
        System.out.println("\n页面调入顺序:");
        operations.forEach(System.out::println);
        double faultRate = (double) pageFaults / SEQUENCE_LENGTH * 100;
        System.out.printf("缺页次数: %d, 缺页率: %.2f%%\n", pageFaults, faultRate);
    }
}

