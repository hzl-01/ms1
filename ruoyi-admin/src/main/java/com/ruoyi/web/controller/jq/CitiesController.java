package com.ruoyi.web.controller.jq;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.jq.domain.Cities;
import com.ruoyi.jq.service.ICitiesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 城市增删改查Controller
 * 
 * @author hzl
 * @date 2025-09-05
 */
@RestController
@RequestMapping("/jq/cities")
public class CitiesController extends BaseController
{
    @Autowired
    private ICitiesService citiesService;

    /**
     * 查询城市增删改查列表
     */
    @PreAuthorize("@ss.hasPermi('jq:cities:list')")
    @GetMapping("/list")
    public TableDataInfo list(Cities cities)
    {
        startPage();
        List<Cities> list = citiesService.selectCitiesList(cities);
        return getDataTable(list);
    }

    /**
     * 导出城市增删改查列表
     */
    @PreAuthorize("@ss.hasPermi('jq:cities:export')")
    @Log(title = "城市增删改查", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Cities cities)
    {
        List<Cities> list = citiesService.selectCitiesList(cities);
        ExcelUtil<Cities> util = new ExcelUtil<Cities>(Cities.class);
        util.exportExcel(response, list, "城市增删改查数据");
    }

    /**
     * 获取城市增删改查详细信息
     */
    @PreAuthorize("@ss.hasPermi('jq:cities:query')")
    @GetMapping(value = "/{cityId}")
    public AjaxResult getInfo(@PathVariable("cityId") Long cityId)
    {
        return success(citiesService.selectCitiesByCityId(cityId));
    }

    /**
     * 新增城市增删改查
     */
    @PreAuthorize("@ss.hasPermi('jq:cities:add')")
    @Log(title = "城市增删改查", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Cities cities)
    {
        return toAjax(citiesService.insertCities(cities));
    }

    /**
     * 修改城市增删改查
     */
    @PreAuthorize("@ss.hasPermi('jq:cities:edit')")
    @Log(title = "城市增删改查", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Cities cities)
    {
        return toAjax(citiesService.updateCities(cities));
    }

    /**
     * 删除城市增删改查
     */
    @PreAuthorize("@ss.hasPermi('jq:cities:remove')")
    @Log(title = "城市增删改查", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cityIds}")
    public AjaxResult remove(@PathVariable Long[] cityIds)
    {
        return toAjax(citiesService.deleteCitiesByCityIds(cityIds));
    }
}
