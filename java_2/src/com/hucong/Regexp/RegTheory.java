package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTheory {
    public static void main(String[] args) {
        String content = "我记得那是在1998年的夏天，我第一次接触到了互联网。" +
                "当时拨号上网的号码还是1630，网速慢得惊人。" +
                "到了2005年，我家终于装上了宽带，账号密码还是8888这样的简单组合。" +
                "大学时代最难忘的是2012年，那年我们宿舍四个人通宵玩游戏的场景至今难忘。" +
                "毕业后第一份工作月薪4500，虽然不多但在2016年也够生活了。" +
                "最近整理老照片时，发现一张1999年全家福，背景里的车牌号6688特别显眼。";
        // \\d表示一个任意的数字
        //还可以进行分组
        String regStr = "(\\d\\d)(\\d\\d)";
        //构建模式对象 正则表达式对象
        Pattern pattern = Pattern.compile(regStr);
        //创建匹配器 matcher 按照正则表达式的规则 去匹配content字符串
        Matcher matcher = pattern.matcher(content);
        //开始匹配
        /**
         *
         * matcher.find()分析
         * 1 按照指定的规则 定位满足规则的子字符串 比如1998
         * 2 找到后 将子字符串的开始的索引记录到matcher对象的属性 int[] groups;
         * 3 groups[0] = 0 把该子字符串的结束的索引+1的值记录到groups[1] = 4
         * 4 同时记录oldList的值子字符串的结束的索引+1即4 则下次执行find时就从 4 开始匹配查询
         *
         * matcher.group(0)分析源码
         * public String group(int group) {
         *         if (first < 0)
         *             throw new IllegalStateException("No match found");
         *         if (group < 0 || group > groupCount())
         *             throw new IndexOutOfBoundsException("No group " + group);
         *         if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
         *             return null;
         *         return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
         *     }
         *     1 根据groups[0] = 0 和 groups[1] = 4 的记录的位置 从content开始截取子字符串 就是[0,4)
         *
         *   matcher.group(1)分析源码
         *           public String group(int group) {
         *                  if (first < 0)
         *                     throw new IllegalStateException("No match found");
         *                   if (group < 0 || group > groupCount())
         *                      throw new IndexOutOfBoundsException("No group " + group);
         *                   if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
         *                       return null;
         *                   return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
         *               }
         *   1 groups[0] = 0 把该子字符串的结束的索引+1的值记录到groups[1] = 4
         *   2 记录1组()匹配的字符串 groups[2] = 0 groups[3] = 2
         *   3 记录2组()匹配的字符串 groups[4] =2 groups[5] = 4
         */
        while (matcher.find()){
            //groups[0] 表示匹配到子字符串
            //groups[1] 表示匹配到的子字符串第一组字符串
            //groups[2] 表示匹配到的子字符串第二组字符串
            //但是分组不能越界
            System.out.println("找到:" + matcher.group(0));
            System.out.println("找到:" + matcher.group(1));
            System.out.println("找到:" + matcher.group(2));
        }
    }
}
