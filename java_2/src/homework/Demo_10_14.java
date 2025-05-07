package homework;

public class Demo_10_14 {
    public static void main(String[] args) {
        Demo_10_14_1.oo();
        Demo_10_14_1.ii();
        Demo_10_14_1.dd();
        Demo_10_14_1.ee();
        Demo_10_14_1.ll();
        Demo_10_14_1.qq();
        Demo_10_14_1.uu();
    }
}

class Demo_10_14_1 {
    public static void oo() {
        int[][] arr = {{12, 4, 8, 9}, {17, 10, 5, 11}, {7, 3, 1, 14}, {20, 21, 2, 6}};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr[i].length; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        System.out.println("===============");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void uu() {
        String[][] arr = new String[3][2];
        arr[0][0] = "历史";
        arr[1][0] = "经济";
        arr[2][1] = "现代科学";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void ii() {
        char[][] arr = {
                {'春', '眠', '不', '觉', '晓'},
                {'处', '处', '闻', '啼', '鸟'},
                {'夜', '来', '风', '雨', '声'},
                {'花', '落', '知', '多', '少'}
        };
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        char[][] arr2 = new char[5][4];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr2[j][i] = arr[i][j];
            }
        }
        System.out.println("==============");
        for (int j = 0; j < arr2.length; j++) {
            for (int k = 0; k < arr2[j].length; k++) {
                System.out.print(arr2[j][k] + " ");
            }
            System.out.println();
        }
    }

    public static void dd() {
        int[][] arr = new int[10][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[i + 1];
            arr[i][i] = 1;
            arr[i][0] = 1;
            for (int j = 1; j < i; j++) {
                arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void ll() {
        int[] arr = {10, 11, 13, 14, 15};
        int score = 12;
        int[] arr2 = new int[arr.length + 1];
        for (int i = 0; i < 2; i++) {
            arr2[i] = arr[i];
        }
        arr2[2] = score;
        for (int i = 3; i < arr2.length; i++) {
            arr2[i] = arr[i - 1];
        }
        System.out.println("添加新元素之前的数组：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
        System.out.println("添加新元素之后的数组：");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + "\t");
        }
    }

    public static void qq() {
        int[] arr = {9, 8, 3, 5, 2};

        //遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "");
        }
        System.out.println();
        //冒泡
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        //遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "");
        }
    }

    public static void ee() {
        int[][] arr = new int[3][];
        arr[0] = new int[]{11, 12};
        arr[1] = new int[]{21, 22, 23};
        arr[2] = new int[]{31, 32, 33, 34};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int groupSum = 0;
            for (int j = 0; j < arr[i].length; j++) {
                groupSum = groupSum + arr[i][j];
            }
            System.out.println("第" + (i + 1) + "小组销售额为:" + groupSum + "万元");
            sum = sum + groupSum;
        }
        System.out.println("总销售额为:" + sum + "万元");
    }
}

