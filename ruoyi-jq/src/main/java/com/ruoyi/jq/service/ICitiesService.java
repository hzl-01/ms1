package com.ruoyi.jq.service;

import java.util.List;
import com.ruoyi.jq.domain.Cities;
import com.ruoyi.jq.domain.DTO.AreaCreateDTO;

/**
 * 城市增删改查Service接口
 * 
 * @author hzl
 * @date 2025-09-05
 */
public interface ICitiesService 
{
    /**
     * 查询城市增删改查
     * 
     * @param cityId 城市增删改查主键
     * @return 城市增删改查
     */
    public Cities selectCitiesByCityId(Long cityId);

    /**
     * 查询城市增删改查列表
     * 
     * @param cities 城市增删改查
     * @return 城市增删改查集合
     */
    public List<Cities> selectCitiesList(Cities cities);

    /**
     * 新增城市增删改查
     * 
     * @param cities 城市增删改查
     * @return 结果
     */
    public int insertCities(Cities cities);

    /**
     * 修改城市增删改查
     * 
     * @param cities 城市增删改查
     * @return 结果
     */
    public int updateCities(Cities cities);

    /**
     * 批量删除城市增删改查
     * 
     * @param cityIds 需要删除的城市增删改查主键集合
     * @return 结果
     */
    public int deleteCitiesByCityIds(Long[] cityIds);

    /**
     * 删除城市增删改查信息
     * 
     * @param cityId 城市增删改查主键
     * @return 结果
     */
    public int deleteCitiesByCityId(Long cityId);
}
