package com.ruoyi.jq.domain.DTO;

import java.util.List;
import java.util.Objects;

public class CityWithAddressesDTO {
    private Long cityId;
    private List<String> addresses;

    // 默认构造函数
    public CityWithAddressesDTO() {
    }

    // 带参构造函数
    public CityWithAddressesDTO(Long cityId, List<String> addresses) {
        this.cityId = cityId;
        this.addresses = addresses;
    }

    // Getter 和 Setter 方法
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
                Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, addresses);
    }

    // toString 方法
    @Override
    public String toString() {
        return "CityWithAddressesDTO{" +
                "cityId=" + cityId +
                ", addresses=" + addresses +
                '}';
    }
}