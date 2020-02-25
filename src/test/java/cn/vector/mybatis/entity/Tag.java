package cn.vector.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class Tag implements Serializable {
    private Integer id;
    private String type;
    private String name;
    private String status;
    private String createby;
    private Date createTime;
    private String updateby;
    private Date updateTime;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", createby='" + createby + '\'' +
                ", createTime=" + createTime +
                ", updateby='" + updateby + '\'' +
                ", updateTime=" + updateTime +
                ", note='" + note + '\'' +
                '}';
    }
}
