package com.hucong.collection_.map_;

import java.util.HashMap;
import java.util.Map;
@SuppressWarnings({"all"})
public class Map_ {
    public static void main(String[] args) {
        /**
         * 接口实现类的特点 使用实现类HashMap
         * 1 Map 与 Collection 并列存在 用于保存任何有映射关系的数据 key-value (双列元素)
         * 2 Map 中的key 和 value 可以是任何引用类型的数据 会封装到HashMap$Node 对象中
         * 3 Map 中的 key 不能重复 原因和hashset 一样(存入的顺序与输出的顺序不同) 但value可以是相同的
         * 4 Map 的key可以是null 但只能有一个  value也可以是null 但可以有多个
         * 5 当有相同的key时 但value的值不同时 后面的value会替换掉前面的value
         * 6 常用String类作为Map的key 但key和value都是object对象 所以不单单只有String
         * 7 key 和 value 之间存在单向一对一关系 即通过指定的 key 总能找到对应的 value
         */

        Map map = new HashMap();
        map.put("no1","eula");
        map.put("no2","ganYu");
        map.put("no3","hu");
        map.put("no2","keQing");//将ganYu替换成keQing
        map.put(null,null);//key 为 null只能有一个 但value可以有多个
        map.put("no4",null);
        map.put(new Object(),"lisa");//可以直接是一个object对象

        System.out.println("map=" + map);
        System.out.println(map.get("no1"));
    }
}
