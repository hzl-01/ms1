package com.h.jq.service;

import java.util.List;
import com.h.jq.domain.Areas;
import com.h.jq.domain.DTO.AreaCreateDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区域Service接口
 * 
 * @author hzl
 * @date 2025-09-05
 */
public interface IAreasService 
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
     * 批量删除区域
     * 
     * @param areaIds 需要删除的区域主键集合
     * @return 结果
     */
    public int deleteAreasByAreaIds(Long[] areaIds);

    /**
     * 删除区域信息
     * 
     * @param areaId 区域主键
     * @return 结果
     */
    public int deleteAreasByAreaId(Long areaId);


    public int insertAreaByCityId(AreaCreateDTO areaCreateDTO);

    AreaCreateDTO getAreaDetailById(Long areaId);

    List<AreaCreateDTO> getAreaList(AreaCreateDTO dto);

    //更新
    @Transactional
    int updateAreaByCityId(AreaCreateDTO dto);

    @Transactional
    int delelistorone(Long[] areaIds);
}
