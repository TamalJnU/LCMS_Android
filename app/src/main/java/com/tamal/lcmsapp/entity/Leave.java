package com.tamal.lcmsapp.entity;

public class Leave {

    private Integer id;
    private String userId;
    private String name;
    private String email;
    private String role;
    private String apply_date;
    private String leave_from;
    private String leave_to;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getApply_date() {
        return apply_date;
    }

    public void setApply_date(String apply_date) {
        this.apply_date = apply_date;
    }

    public String getLeave_from() {
        return leave_from;
    }

    public void setLeave_from(String leave_from) {
        this.leave_from = leave_from;
    }

    public String getLeave_to() {
        return leave_to;
    }

    public void setLeave_to(String leave_to) {
        this.leave_to = leave_to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
