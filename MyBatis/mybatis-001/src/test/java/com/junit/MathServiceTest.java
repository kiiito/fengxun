package com.junit;

import org.junit.Assert;
import org.junit.Test;

public class MathServiceTest {
    @Test
    public void testSum(){
        /**
         * 期望值 执行了这个业务方法后 你的期望执行的结果是多少
         * 实际值 被测试的业务的真正执行结果
         */

        MathService mathService = new MathService();
        //获取实际值
        int actual = mathService.sum(2, 4);
        //期望值
        int expected = 6;
        //加断言进行测试
        Assert.assertEquals(expected,actual);
    }
}
