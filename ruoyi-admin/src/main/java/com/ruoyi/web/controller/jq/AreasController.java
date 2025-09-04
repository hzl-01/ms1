package com.ruoyi.web.controller.jq;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.jq.domain.DTO.AreaCreateDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.jq.domain.Areas;
import com.ruoyi.jq.service.IAreasService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
    public TableDataInfo list(Areas areas)
    {
        startPage();
        List<AreaCreateDTO> list = areasService.getAreaList();
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
