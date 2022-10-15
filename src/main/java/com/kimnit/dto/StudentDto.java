package com.kimnit.dto;

import com.kimnit.entity.Student;

public class StudentDto extends Student{

    private Integer dormmid;

    private Integer floor;

    public Integer getDormmid() {
        return dormmid;
    }

    public void setDormmid(Integer dormmid) {
        this.dormmid = dormmid;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}
