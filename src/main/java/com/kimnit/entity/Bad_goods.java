package com.kimnit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Bad_goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号
     */
    private String stuid;

    /**
     * 上报日期
     */
    private LocalDateTime subtime;

    /**
     * 解决日期，空表示未解决
     */
    private LocalDateTime rsotime;

    /**
     * 表示需要维修的物品
     */
    private String goods;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStu_id() {
        return stuid;
    }

    public void setStu_id(String stu_id) {
        this.stuid = stu_id;
    }

    public LocalDateTime getSubtime() {
        return subtime;
    }

    public void setSubtime(LocalDateTime subtime) {
        this.subtime = subtime;
    }

    public LocalDateTime getRsotime() {
        return rsotime;
    }

    public void setRsotime(LocalDateTime rsotime) {
        this.rsotime = rsotime;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Bad_goods{" +
        "id=" + id +
        ", stu_id=" + stuid +
        ", subtime=" + subtime +
        ", rsotime=" + rsotime +
        ", goods=" + goods +
        "}";
    }
}
