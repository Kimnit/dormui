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
public class Violation implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String stuid;

    /**
     * 违纪时间
     */
    private LocalDateTime wtime;

    /**
     * 违纪原因
     */
    private String wreason;


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

    public LocalDateTime getWtime() {
        return wtime;
    }

    public void setWtime(LocalDateTime wtime) {
        this.wtime = wtime;
    }

    public String getWreason() {
        return wreason;
    }

    public void setWreason(String wreason) {
        this.wreason = wreason;
    }

    @Override
    public String toString() {
        return "Violation{" +
        "id=" + id +
        ", stu_id=" + stuid +
        ", wtime=" + wtime +
        ", wreason=" + wreason +
        "}";
    }
}
