package com.hc.springboot.ssm.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vip
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vip implements Serializable {
    private Long id;

    private String name;

    private String cardNumber;

    private String birth;

    private static final long serialVersionUID = 1L;
    @Override
    public String toString() {
        return "Vip(id=" + id + ", name=" + name + ", cardNumber=" + cardNumber + ", birth=" + birth + ")";
    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getCardNumber() {
//        return cardNumber;
//    }
//
//    public String getBirth() {
//        return birth;
//    }
}