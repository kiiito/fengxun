package com.hc.springboot.bean;

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
}