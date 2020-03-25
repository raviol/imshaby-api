package by.imsha.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author Alena Misan
 */
public class MassParishInfo implements Serializable {
    private String parishId;
    private String name;
    private String imgPath;
    private LocationInfo gps;
    private String address;
    private boolean needUpdate;
    private String broadcastUrl;


    @JsonIgnore
    private Integer updatePeriodInDays;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MassParishInfo)) return false;

        MassParishInfo that = (MassParishInfo) o;

        if (!parishId.equals(that.parishId)) return false;
        if (!name.equals(that.name)) return false;
        if (imgPath != null ? !imgPath.equals(that.imgPath) : that.imgPath != null) return false;
        if (gps != null ? !gps.equals(that.gps) : that.gps != null) return false;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = parishId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + (gps != null ? gps.hashCode() : 0);
        result = 31 * result + address.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MassParishInfo{" +
                "parishId='" + parishId + '\'' +
                ", name='" + name + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", gps=" + gps +
                ", address='" + address + '\'' +
                ", needUpdate=" + needUpdate +
                ", updatePeriodInDays=" + updatePeriodInDays +
                '}';
    }

    public String getParishId() {
        return parishId;
    }

    public void setParishId(String parishId) {
        this.parishId = parishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public LocationInfo getGps() {
        return gps;
    }

    public void setGps(LocationInfo gps) {
        this.gps = gps;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public Integer getUpdatePeriodInDays() {
        return updatePeriodInDays;
    }

    public String getBroadcastUrl() {
        return broadcastUrl;
    }

    public void setBroadcastUrl(String broadcastUrl) {
        this.broadcastUrl = broadcastUrl;
    }

    public void setUpdatePeriodInDays(Integer updatePeriodInDays) {
        this.updatePeriodInDays = updatePeriodInDays;
    }
}
