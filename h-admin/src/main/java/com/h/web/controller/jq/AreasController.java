package com.h.web.controller.jq;

import java.util.List;
import javax.servlet.http.HttpServletResponse;


import com.h.common.core.page.PageDomain;
import com.h.jq.domain.DTO.AreaCreateDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.h.common.annotation.Log;
import com.h.common.core.controller.BaseController;
import com.h.common.core.domain.AjaxResult;
import com.h.common.enums.BusinessType;
import com.h.jq.domain.Areas;
import com.h.jq.service.IAreasService;
import com.h.common.utils.poi.ExcelUtil;
import com.h.common.core.page.TableDataInfo;

/**
 * 区域Controller
 * 
 * @author hzl
 * @date 2025-09-05
 */
@RestController
@RequestMapping("/jq/areas")
public class AreasController extends BaseController
{
    @Autowired
    private IAreasService areasService;

    /**
     * 查询区域列表
     */
    @PreAuthorize("@ss.hasPermi('jq:areas:list')")
    @GetMapping("/list")
    public TableDataInfo list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "areaName", required = false) String areaName,
            @RequestParam(value = "cityName", required = false) String cityName,
            @RequestParam(value = "addressName", required = false) String addressText) {

        // 创建查询条件对象
        AreaCreateDTO queryParams = new AreaCreateDTO();
        queryParams.setAreaName(areaName);
        queryParams.setCityName(cityName);
        queryParams.setaddressName(addressText);

        // 设置分页
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);

        // 执行查询
        startPage();
        List<AreaCreateDTO> list = areasService.getAreaList(queryParams);
        return getDataTable(list);
    }

    /**
     * 导出区域列表
     */
    @PreAuthorize("@ss.hasPermi('jq:areas:export')")
    @Log(title = "区域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Areas areas)
    {
        List<Areas> list = areasService.selectAreasList(areas);
        ExcelUtil<Areas> util = new ExcelUtil<Areas>(Areas.class);
        util.exportExcel(response, list, "区域数据");
    }

    /**
     * 获取区域详细信息
     */
    @PreAuthorize("@ss.hasPermi('jq:areas:query')")
    @GetMapping(value = "/{areaId}")
    public AjaxResult getInfo(@PathVariable("areaId") Long areaId)
    {
        return success(areasService.getAreaDetailById(areaId));
    }

    /**
     * 新增区域
     */
    @PreAuthorize("@ss.hasPermi('jq:areas:add')")
    @Log(title = "区域", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AreaCreateDTO areaCreateDT)
    {
        return toAjax(areasService.insertAreaByCityId( areaCreateDT));
    }

    /**
     * 修改区域
     */
    @PreAuthorize("@ss.hasPermi('jq:areas:edit')")
    @Log(title = "区域", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AreaCreateDTO AreaCreateDTO)
    {
        return toAjax(areasService.updateAreaByCityId(AreaCreateDTO));
    }

    /**
     * 删除区域
     */
    @PreAuthorize("@ss.hasPermi('jq:areas:remove')")
    @Log(title = "区域", businessType = BusinessType.DELETE)
	@DeleteMapping("/{areaIds}")
    public AjaxResult remove(@PathVariable Long[] areaIds)
    {
        return toAjax(areasService.delelistorone(areaIds));
    }


}
