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
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String stuid;

    /**
     * 0表示未打卡，其他表示已打卡
     */
    private Integer sure;

    /**
     * 打卡时间
     */
    private LocalDateTime ctime;


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

    public Integer getSure() {
        return sure;
    }

    public void setSure(Integer sure) {
        this.sure = sure;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Card{" +
        "id=" + id +
        ", stu_id=" + stuid +
        ", sure=" + sure +
        ", ctime=" + ctime +
        "}";
    }
}
