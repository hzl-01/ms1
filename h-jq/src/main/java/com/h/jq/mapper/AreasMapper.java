package com.h.jq.mapper;

import java.util.List;
import com.h.jq.domain.Areas;
import com.h.jq.domain.DTO.AreaCreateDTO;

/**
 * 区域Mapper接口
 * 
 * @author hzl
 * @date 2025-09-05
 */
public interface AreasMapper 
{
    /**
     * 查询区域
     * 
     * @param areaId 区域主键
     * @return 区域
     */
    public Areas selectAreasByAreaId(Long areaId);

    /**
     * 查询区域列表
     * 
     * @param areas 区域
     * @return 区域集合
     */
    public List<Areas> selectAreasList(Areas areas);

    /**
     * 新增区域
     * 
     * @param areas 区域
     * @return 结果
     */
    public int insertAreas(Areas areas);

    /**
     * 修改区域
     * 
     * @param areas 区域
     * @return 结果
     */
    public int updateAreas(Areas areas);

    /**
     * 删除区域
     * 
     * @param areaId 区域主键
     * @return 结果
     */
    public int deleteAreasByAreaId(Long areaId);

    /**
     * 批量删除区域
     * 
     * @param areaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAreasByAreaIds(Long[] areaIds);


    /**
     * 查询区域及其关联的城市和地址信息
     *
     * @param areas 区域查询条件
     * @return 区域列表
     */
    List<AreaCreateDTO> selectAreasWithCitiesAndAddresses(Areas areas);
}
