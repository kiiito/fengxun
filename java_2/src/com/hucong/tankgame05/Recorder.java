package com.hucong.tankgame05;

import java.io.*;
import java.util.Vector;

public class Recorder {
    //定义变量 记录我方击毁敌人坦克数量
    private static int allEnemyTank = 0;
    //定义IO对象 准备写数据到文件中
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src/com/hucong/tankgame05/myRecord.txt";
    //定义vector 指向MyPanel 对象 的坦克的vector
    private static Vector<Enemy> enemies = null;
    private static Vector<Node> nodes = new Vector<>();
    public static void setEnemies(Vector<Enemy> enemies) {
        Recorder.enemies = enemies;
    }
    public static Vector<Node> getNodesAndEnemiesRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            //先读取击杀个数
            allEnemyTank = Integer.parseInt(br.readLine());
            //循环读取文件 生成nodes集合
            String line = "";
            while ((line = br.readLine())!=null){
                String[] split = line.split(" ");
                Node node = new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }
    public static int getAllEnemyTank() {
        return allEnemyTank;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    public static void setAllEnemyTank(int allEnemyTank) {
        Recorder.allEnemyTank = allEnemyTank;
    }
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTank++;
    }
    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTank+"\r\n");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if (enemy.isLive){
                    String record = enemy.getX() + " " + enemy.getY() + " " + enemy.getDirect();
                    bw.write(record);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
