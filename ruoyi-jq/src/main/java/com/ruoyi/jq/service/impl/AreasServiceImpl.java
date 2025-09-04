package com.ruoyi.jq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jq.mapper.AreasMapper;
import com.ruoyi.jq.domain.Areas;
import com.ruoyi.jq.service.IAreasService;

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
}
