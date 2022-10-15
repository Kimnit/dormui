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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userid;

    private String name;

    private String phone;

    private String password;

    /**
     * 1为管理员，0为学生
     */
    private Integer role;

    /**
     * 辅导员
     */
    private String teacher;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return userid;
    }

    public void setUser_id(String user_id) {
        this.userid = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
        "id=" + id +
        ", user_id=" + userid +
        ", name=" + name +
        ", phone=" + phone +
        ", password=" + password +
        ", role=" + role +
        ", teacher=" + teacher +
        "}";
    }
}
