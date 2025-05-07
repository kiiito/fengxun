package com.hucong.annotation_;

import java.util.ArrayList;
import java.util.List;

public class SuppressWarnings_ {
    // 1 当我们不希望看到这些警告 可以使用 @SuppressWarnings 来注解抑制警告信息
    // 2  在{""}中 可以写入你希望不显示的警告信息 all是所有
    // 3 关于@SuppressWarnings 作用范围是和你放置的位置相关
    // 比如 @SuppressWarnings 放在main方法中 那么不显示的范围就是main
    @SuppressWarnings({"rawtypes","unchecked","unused","update"})
    public static void main(String[] args) {
        List ll = new ArrayList();
        ll.add("jack");
        ll.add("tom");
        int i;
    }

}
