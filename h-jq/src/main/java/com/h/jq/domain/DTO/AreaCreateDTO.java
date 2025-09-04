package com.h.jq.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AreaCreateDTO {
    private Long areaId;
    private String areaName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;   // 添加
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;   // 添加
    private List<CityWithAddressesDTO> cities;

    // 默认构造函数
    public AreaCreateDTO() {
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    // 带参构造函数
    public AreaCreateDTO(Long areaId, String areaName, List<CityWithAddressesDTO> cities) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.cities = cities;
    }

    // Getter 和 Setter 方法
    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<CityWithAddressesDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityWithAddressesDTO> cities) {
        this.cities = cities;
    }

    // equals 和 hashCode 方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaCreateDTO that = (AreaCreateDTO) o;
        return Objects.equals(areaId, that.areaId) &&
                Objects.equals(areaName, that.areaName) &&
                Objects.equals(cities, that.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaId, areaName, cities);
    }

    // toString 方法
    @Override
    public String toString() {
        return "AreaCreateDTO{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", cities=" + cities +
                '}';
    }
}