package com.kimnit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-09-08
 */
@Data
public class Dormm implements Serializable {

    private static final long serialVersionUID = 1L;

      private String stuid;

    private Integer floor;

    private Integer dormid;


    public String getStu_id() {
        return stuid;
    }

    public void setStu_id(String stu_id) {
        this.stuid = stu_id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getDormid() {
        return dormid;
    }

    public void setDormid(Integer dormid) {
        this.dormid = dormid;
    }

    @Override
    public String toString() {
        return "Dormm{" +
        "stu_id=" + stuid +
        ", floor=" + floor +
        ", dorm_id=" + dormid +
        "}";
    }
}
