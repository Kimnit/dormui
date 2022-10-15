package com.kimnit.entity;

import lombok.Data;

import java.time.LocalDateTime;
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
public class Leav implements Serializable {

    private static final long serialVersionUID = 1L;

    private String stuid;

    private Integer floor;

    private Integer dormid;

    private LocalDateTime ltime;

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

    public Integer getDorm_id() {
        return dormid;
    }

    public void setDorm_id(Integer dorm_id) {
        this.dormid = dorm_id;
    }

    public LocalDateTime getL_time() {
        return ltime;
    }

    public void setL_time(LocalDateTime l_time) {
        this.ltime = l_time;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "stu_id='" + stuid + '\'' +
                ", floor=" + floor +
                ", dorm_id=" + dormid +
                ", l_time=" + ltime +
                '}';
    }
}
