package com.h.jq.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CityWithAddressesDTO {
    private Long cityId;
    private String cityName; // 新增字段
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;    // 添加
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;    // 添加
    private List<String> addresses;

    // 默认构造函数
    public CityWithAddressesDTO() {
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
    public CityWithAddressesDTO(Long cityId, String cityName, List<String> addresses) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.addresses = addresses;
    }

    // Getter 和 Setter 方法
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    // equals 和 hashCode 方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityWithAddressesDTO that = (CityWithAddressesDTO) o;
        return Objects.equals(cityId, that.cityId) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName, addresses);
    }

    // toString 方法
    @Override
    public String toString() {
        return "CityWithAddressesDTO{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}