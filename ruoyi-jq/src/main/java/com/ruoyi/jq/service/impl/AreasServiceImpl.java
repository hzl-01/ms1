package com.ruoyi.jq.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.jq.domain.Addresses;
import com.ruoyi.jq.domain.Cities;
import com.ruoyi.jq.domain.DTO.AreaCreateDTO;
import com.ruoyi.jq.domain.DTO.CityWithAddressesDTO;
import com.ruoyi.jq.mapper.AddressesMapper;
import com.ruoyi.jq.mapper.CitiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jq.mapper.AreasMapper;
import com.ruoyi.jq.domain.Areas;
import com.ruoyi.jq.service.IAreasService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区域Service业务层处理
 *
 * @author hzl
 * @date 2025-09-05
 */
@Service
public class AreasServiceImpl implements IAreasService
{
    @Autowired
    private AreasMapper areasMapper;

    @Autowired
    private CitiesMapper citiesMapper ;

    @Autowired
    private AddressesMapper addressesMapper ;

    /**
     * 查询区域
     *
     * @param areaId 区域主键
     * @return 区域
     */
    @Override
    public Areas selectAreasByAreaId(Long areaId)
    {
        return areasMapper.selectAreasByAreaId(areaId);
    }

    /**
     * 查询区域列表
     *
     * @param areas 区域
     * @return 区域
     */
    @Override
    public List<Areas> selectAreasList(Areas areas)
    {
        return areasMapper.selectAreasList(areas);
    }

    /**
     * 新增区域
     *
     * @param areas 区域
     * @return 结果
     */
    @Override
    public int insertAreas(Areas areas)
    {
        return areasMapper.insertAreas(areas);
    }

    /**
     * 修改区域
     *
     * @param areas 区域
     * @return 结果
     */
    @Override
    public int updateAreas(Areas areas)
    {
        return areasMapper.updateAreas(areas);
    }

    /**
     * 批量删除区域
     *
     * @param areaIds 需要删除的区域主键
     * @return 结果
     */
    @Override
    public int deleteAreasByAreaIds(Long[] areaIds)
    {
        return areasMapper.deleteAreasByAreaIds(areaIds);
    }

    /**
     * 删除区域信息
     *
     * @param areaId 区域主键
     * @return 结果
     */
    @Override
    public int deleteAreasByAreaId(Long areaId)
    {
        return areasMapper.deleteAreasByAreaId(areaId);
    }



    @Override
    @Transactional
    public int insertAreaByCityId(AreaCreateDTO dto) {
        // 1. 检查区域名称是否已存在
        String areaName = dto.getAreaName();
        Areas areaQuery = new Areas();
        areaQuery.setAreaName(areaName);
        List<Areas> existingAreas = areasMapper.selectAreasList(areaQuery);

        if (!existingAreas.isEmpty()) {
            throw new GlobalException("区域名称已存在")
                    .setDetailMessage("区域名称: " + dto.getAreaName());
        }

        // 2. 创建新区域
        Areas newArea = new Areas();
        newArea.setAreaName(dto.getAreaName());
        // 添加时间戳（如果数据库需要）
        newArea.setCreatedAt(new Date());
        newArea.setUpdatedAt(new Date());

        int areaResult = areasMapper.insertAreas(newArea);

        if (areaResult <= 0) {
            throw new GlobalException("创建区域失败")
                    .setDetailMessage("区域名称: " + dto.getAreaName());
        }

        Long areaId = newArea.getAreaId();

        // 3. 关联城市和地址
        List<CityWithAddressesDTO> cityDtos = dto.getCities();
        if (cityDtos != null && !cityDtos.isEmpty()) {
            for (CityWithAddressesDTO cityDto : cityDtos) {
                // 检查城市是否存在
                Cities city = citiesMapper.selectCitiesByCityId(cityDto.getCityId());
                if (city == null) {
                    throw new GlobalException("城市不存在")
                            .setDetailMessage("城市ID: " + cityDto.getCityId());
                }

                // 检查城市是否已属于其他区域
                if (city.getAreaId() != null && !city.getAreaId().equals(areaId)) {
                    throw new GlobalException("城市已属于其他区域")
                            .setDetailMessage("城市: " + city.getCityName() + " 已属于区域ID: " + city.getAreaId());
                }

                // 更新城市的区域关联 - 关键修复点
                Cities updateCity = new Cities();
                updateCity.setCityId(cityDto.getCityId());
                updateCity.setAreaId(areaId);
                // 添加更新时间戳（如果数据库需要）
                updateCity.setUpdatedAt(new Date());

                int cityResult = citiesMapper.updateCities(updateCity);

                if (cityResult <= 0) {
                    throw new GlobalException("更新城市区域关联失败")
                            .setDetailMessage("城市ID: " + cityDto.getCityId() + ", 区域ID: " + areaId);
                }

                // 添加地址 - 另一个关键修复点
                if (cityDto.getAddresses() != null && !cityDto.getAddresses().isEmpty()) {
                    for (String addressText : cityDto.getAddresses()) {
                        Addresses address = new Addresses();
                        address.setCityId(cityDto.getCityId());
                        address.setAddressText(addressText);
                        // 添加时间戳（如果数据库需要）
                        address.setCreatedAt(new Date());
                        address.setUpdatedAt(new Date());

                        int addressResult = addressesMapper.insertAddresses(address);

                        if (addressResult <= 0) {
                            throw new GlobalException("添加地址失败")
                                    .setDetailMessage("城市ID: " + cityDto.getCityId() + ", 地址: " + addressText);
                        }
                    }
                }
            }
        }

        return 1; // 返回成功
    }


    @Override
    public AreaCreateDTO getAreaDetailById(Long areaId) {
        // 1. 查询区域基本信息
        Areas area = areasMapper.selectAreasByAreaId(areaId);
        if (area == null) {
            throw new GlobalException("区域不存在")
                    .setDetailMessage("区域ID: " + areaId);
        }

        // 2. 创建返回对象
        AreaCreateDTO areaDetail = new AreaCreateDTO();
        areaDetail.setAreaId(area.getAreaId());
        areaDetail.setAreaName(area.getAreaName());

        // 3. 查询该区域下的所有城市
        Cities cityQuery = new Cities();
        cityQuery.setAreaId(areaId);
        List<Cities> cities = citiesMapper.selectCitiesList(cityQuery);

        // 4. 为每个城市查询地址信息并构建CityWithAddressesDTO
        List<CityWithAddressesDTO> cityDtos = new ArrayList<>();
        for (Cities city : cities) {
            CityWithAddressesDTO cityDto = new CityWithAddressesDTO();
            cityDto.setCityId(city.getCityId());
            cityDto.setCityName(city.getCityName()); // 设置城市名称

            // 查询该城市的地址
            Addresses addressQuery = new Addresses();
            addressQuery.setCityId(city.getCityId());
            List<Addresses> addresses = addressesMapper.selectAddressesList(addressQuery);

            // 提取地址文本
            List<String> addressTexts = addresses.stream()
                    .map(Addresses::getAddressText)
                    .collect(Collectors.toList());

            cityDto.setAddresses(addressTexts);
            cityDtos.add(cityDto);
        }

        areaDetail.setCities(cityDtos);
        return areaDetail;
    }

//返回列表
@Override
public List<AreaCreateDTO> getAreaList() {
    // 使用新的查询方法获取所有数据
    return areasMapper.selectAreasWithCitiesAndAddresses(new Areas());
}

//更新
@Override
@Transactional
public int updateAreaByCityId(AreaCreateDTO dto) {
    // 1. 检查区域是否存在
    if (dto.getAreaId() == null) {
        throw new GlobalException("区域ID不能为空");
    }

    Areas existingArea = areasMapper.selectAreasByAreaId(dto.getAreaId());
    if (existingArea == null) {
        throw new GlobalException("区域不存在")
                .setDetailMessage("区域ID: " + dto.getAreaId());
    }

    // 2. 检查区域名称是否已存在（排除自身）
    if (!existingArea.getAreaName().equals(dto.getAreaName())) {
        Areas areaQuery = new Areas();
        areaQuery.setAreaName(dto.getAreaName());
        List<Areas> existingAreas = areasMapper.selectAreasList(areaQuery);

        if (!existingAreas.isEmpty()) {
            throw new GlobalException("区域名称已存在")
                    .setDetailMessage("区域名称: " + dto.getAreaName());
        }
    }

    // 3. 更新区域基本信息
    Areas updateArea = new Areas();
    updateArea.setAreaId(dto.getAreaId());
    updateArea.setAreaName(dto.getAreaName());
    updateArea.setUpdatedAt(new Date());

    int areaResult = areasMapper.updateAreas(updateArea);

    if (areaResult <= 0) {
        throw new GlobalException("更新区域失败")
                .setDetailMessage("区域ID: " + dto.getAreaId());
    }

    Long areaId = dto.getAreaId();

    // 4. 获取当前区域下的所有城市
    Cities currentCityQuery = new Cities();
    currentCityQuery.setAreaId(areaId);
    List<Cities> currentCities = citiesMapper.selectCitiesList(currentCityQuery);

    // 5. 找出需要解除关联的城市（不在新列表中的城市）
    List<Long> newCityIds = dto.getCities() != null ?
            dto.getCities().stream()
                    .map(CityWithAddressesDTO::getCityId)
                    .collect(Collectors.toList()) :
            new ArrayList<>();

    List<Cities> citiesToRemove = currentCities.stream()
            .filter(city -> !newCityIds.contains(city.getCityId()))
            .collect(Collectors.toList());

    // 6. 解除不需要的城市的关联，并删除它们的地址
    for (Cities city : citiesToRemove) {
        // 先删除该城市的所有地址
        Addresses deleteAddressQuery = new Addresses();
        deleteAddressQuery.setCityId(city.getCityId());
        List<Addresses> existingAddresses = addressesMapper.selectAddressesList(deleteAddressQuery);

        for (Addresses addr : existingAddresses) {
            int addressResult = addressesMapper.deleteAddressesByAddressId(addr.getAddressId());
            if (addressResult <= 0) {
                throw new GlobalException("删除地址失败")
                        .setDetailMessage("地址ID: " + addr.getAddressId());
            }
        }

        // 然后解除城市与区域的关联（设置为null）
        Cities clearCity = new Cities();
        clearCity.setCityId(city.getCityId());
        clearCity.setAreaId(null); // 解除关联
        clearCity.setUpdatedAt(new Date());

        int cityResult = citiesMapper.updateCities(clearCity);
        if (cityResult <= 0) {
            throw new GlobalException("解除城市关联失败")
                    .setDetailMessage("城市ID: " + city.getCityId());
        }
    }

    // 7. 处理新的城市和地址
    List<CityWithAddressesDTO> cityDtos = dto.getCities();
    if (cityDtos != null && !cityDtos.isEmpty()) {
        for (CityWithAddressesDTO cityDto : cityDtos) {
            // 检查城市是否存在
            Cities city = citiesMapper.selectCitiesByCityId(cityDto.getCityId());

            if (city == null) {
                throw new GlobalException("城市不存在")
                        .setDetailMessage("城市ID: " + cityDto.getCityId());
            }

            // 检查城市是否已属于其他区域（如果不是当前区域）
            if (city.getAreaId() != null && !city.getAreaId().equals(areaId)) {
                throw new GlobalException("城市已属于其他区域")
                        .setDetailMessage("城市: " + city.getCityName() + " 已属于区域ID: " + city.getAreaId());
            }

            // 更新城市的区域关联（如果尚未关联）
            if (!areaId.equals(city.getAreaId())) {
                Cities updateCity = new Cities();
                updateCity.setCityId(cityDto.getCityId());
                updateCity.setAreaId(areaId);
                updateCity.setUpdatedAt(new Date());

                int cityResult = citiesMapper.updateCities(updateCity);

                if (cityResult <= 0) {
                    throw new GlobalException("更新城市区域关联失败")
                            .setDetailMessage("城市ID: " + cityDto.getCityId() + ", 区域ID: " + areaId);
                }
            }

            // 处理地址 - 先删除所有现有地址，然后添加新地址
            Addresses deleteAddressQuery = new Addresses();
            deleteAddressQuery.setCityId(cityDto.getCityId());
            List<Addresses> existingAddresses = addressesMapper.selectAddressesList(deleteAddressQuery);

            // 删除所有现有地址
            for (Addresses addr : existingAddresses) {
                int addressResult = addressesMapper.deleteAddressesByAddressId(addr.getAddressId());
                if (addressResult <= 0) {
                    throw new GlobalException("删除地址失败")
                            .setDetailMessage("地址ID: " + addr.getAddressId());
                }
            }

            // 添加新地址
            if (cityDto.getAddresses() != null && !cityDto.getAddresses().isEmpty()) {
                for (String addressText : cityDto.getAddresses()) {
                    Addresses address = new Addresses();
                    address.setCityId(cityDto.getCityId());
                    address.setAddressText(addressText);
                    address.setCreatedAt(new Date());
                    address.setUpdatedAt(new Date());

                    int addressResult = addressesMapper.insertAddresses(address);

                    if (addressResult <= 0) {
                        throw new GlobalException("添加地址失败")
                                .setDetailMessage("城市ID: " + cityDto.getCityId() + ", 地址: " + addressText);
                    }
                }
            }
        }
    }

    return 1; // 返回成功
}

    @Transactional
    @Override
    public int delelistorone(Long[] areaIds) {
        int totalDeleted = 0;

        for (Long areaId : areaIds) {
            // 1. 检查区域是否存在
            Areas existingArea = areasMapper.selectAreasByAreaId(areaId);
            if (existingArea == null) {
                throw new GlobalException("区域不存在")
                        .setDetailMessage("区域ID: " + areaId);
            }

            // 2. 查找该区域下的所有城市
            Cities cityQuery = new Cities();
            cityQuery.setAreaId(areaId);
            List<Cities> cities = citiesMapper.selectCitiesList(cityQuery);

            // 3. 处理每个城市的地址和关联
            for (Cities city : cities) {
                // 删除该城市的所有地址
                Addresses addressQuery = new Addresses();
                addressQuery.setCityId(city.getCityId());
                List<Addresses> addresses = addressesMapper.selectAddressesList(addressQuery);

                for (Addresses address : addresses) {
                    int addressResult = addressesMapper.deleteAddressesByAddressId(address.getAddressId());
                    if (addressResult <= 0) {
                        throw new GlobalException("删除地址失败")
                                .setDetailMessage("地址ID: " + address.getAddressId());
                    }
                }

                // 解除城市与区域的关联
                Cities updateCity = new Cities();
                updateCity.setCityId(city.getCityId());
                updateCity.setAreaId(null); // 设置为null解除关联
                updateCity.setUpdatedAt(new Date());

                int cityResult = citiesMapper.updateCities(updateCity);
                if (cityResult <= 0) {
                    throw new GlobalException("解除城市关联失败")
                            .setDetailMessage("城市ID: " + city.getCityId());
                }
            }

            // 4. 删除区域本身
            int areaResult = areasMapper.deleteAreasByAreaId(areaId);
            if (areaResult <= 0) {
                throw new GlobalException("删除区域失败")
                        .setDetailMessage("区域ID: " + areaId);
            }

            totalDeleted++;
        }

        return totalDeleted;
    }
}
