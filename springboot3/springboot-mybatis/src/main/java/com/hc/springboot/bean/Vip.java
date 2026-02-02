package com.hc.springboot.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @Data 包含getter/setter/equals/hashCode/toString
 * @AllArgsConstructor 表示所有的构造函数
 * @RequiredArgsConstructor 表示所有的必须的构造函数
 * @NOArgsConstructor 表示无参构造函数 与 @RquiredArgsConstructor 冲突
 * vip
 * @author 
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Vip implements Serializable {
    private Long id;

    private String name;

    private String cardNumber;

    private String birth;

    private static final long serialVersionUID = 1L;

    public Vip(String name, String cardNumber, String birth) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.birth = birth;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Vip other = (Vip) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCardNumber() == null ? other.getCardNumber() == null : this.getCardNumber().equals(other.getCardNumber()))
            && (this.getBirth() == null ? other.getBirth() == null : this.getBirth().equals(other.getBirth()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCardNumber() == null) ? 0 : getCardNumber().hashCode());
        result = prime * result + ((getBirth() == null) ? 0 : getBirth().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", cardNumber=").append(cardNumber);
        sb.append(", birth=").append(birth);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}