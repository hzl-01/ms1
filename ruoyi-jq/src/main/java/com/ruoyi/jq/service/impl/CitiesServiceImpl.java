package com.ruoyi.jq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jq.mapper.CitiesMapper;
import com.ruoyi.jq.domain.Cities;
import com.ruoyi.jq.service.ICitiesService;

/**
 * 城市增删改查Service业务层处理
 * 
 * @author hzl
 * @date 2025-09-05
 */
@Service
public class CitiesServiceImpl implements ICitiesService 
{
    @Autowired
    private CitiesMapper citiesMapper;

    /**
     * 查询城市增删改查
     * 
     * @param cityId 城市增删改查主键
     * @return 城市增删改查
     */
    @Override
    public Cities selectCitiesByCityId(Long cityId)
    {
        return citiesMapper.selectCitiesByCityId(cityId);
    }

    /**
     * 查询城市增删改查列表
     * 
     * @param cities 城市增删改查
     * @return 城市增删改查
     */
    @Override
    public List<Cities> selectCitiesList(Cities cities)
    {
        return citiesMapper.selectCitiesList(cities);
    }

    /**
     * 新增城市增删改查
     * 
     * @param cities 城市增删改查
     * @return 结果
     */
    @Override
    public int insertCities(Cities cities)
    {
        return citiesMapper.insertCities(cities);
    }

    /**
     * 修改城市增删改查
     * 
     * @param cities 城市增删改查
     * @return 结果
     */
    @Override
    public int updateCities(Cities cities)
    {
        return citiesMapper.updateCities(cities);
    }

    /**
     * 批量删除城市增删改查
     * 
     * @param cityIds 需要删除的城市增删改查主键
     * @return 结果
     */
    @Override
    public int deleteCitiesByCityIds(Long[] cityIds)
    {
        return citiesMapper.deleteCitiesByCityIds(cityIds);
    }

    /**
     * 删除城市增删改查信息
     * 
     * @param cityId 城市增删改查主键
     * @return 结果
     */
    @Override
    public int deleteCitiesByCityId(Long cityId)
    {
        return citiesMapper.deleteCitiesByCityId(cityId);
    }
}
