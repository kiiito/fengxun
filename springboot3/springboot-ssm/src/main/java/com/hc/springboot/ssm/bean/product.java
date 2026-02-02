package com.hc.springboot.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class product {
    private int id;
    private String goodsname;
    private String price;
    private int typeid;
}
