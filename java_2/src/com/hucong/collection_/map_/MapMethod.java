package com.hucong.collection_.map_;

import java.util.HashMap;
import java.util.Map;
@SuppressWarnings({"all"})
public class MapMethod {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("no1","eula");
        map.put("no2","ganYu");
        map.put("no3","hu");
        map.put("no2","keQing");//将ganYu替换成keQing
        map.put(null,null);//key 为 null只能有一个 但value可以有多个
        map.put("no4",null);
        map.put(new Object(),"lisa");//可以直接是一个object对象

        //remove 根据键删除映射关系
        map.remove("null");
        //get 根据键获取值
         Object val = map.get("no1");
        System.out.println("val =" + val);
        //size 获取元素个数
        System.out.println("k-v = " + map.size());
        //isEmpty 判断个数是否为0
        System.out.println(map.isEmpty());
        //clear 清除
        map.clear();
        System.out.println("map = " + map);
        //cotainsKey 查找键是否存在
        System.out.println(map.containsKey("no1"));
    }
}
