package com.ruoyi.jq.service;

import java.util.List;
import com.ruoyi.jq.domain.Addresses;

/**
 * 地址Service接口
 * 
 * @author hzl
 * @date 2025-09-05
 */
public interface IAddressesService 
{
    /**
     * 查询地址
     * 
     * @param addressId 地址主键
     * @return 地址
     */
    public Addresses selectAddressesByAddressId(Long addressId);

    /**
     * 查询地址列表
     * 
     * @param addresses 地址
     * @return 地址集合
     */
    public List<Addresses> selectAddressesList(Addresses addresses);

    /**
     * 新增地址
     * 
     * @param addresses 地址
     * @return 结果
     */
    public int insertAddresses(Addresses addresses);

    /**
     * 修改地址
     * 
     * @param addresses 地址
     * @return 结果
     */
    public int updateAddresses(Addresses addresses);

    /**
     * 批量删除地址
     * 
     * @param addressIds 需要删除的地址主键集合
     * @return 结果
     */
    public int deleteAddressesByAddressIds(Long[] addressIds);

    /**
     * 删除地址信息
     * 
     * @param addressId 地址主键
     * @return 结果
     */
    public int deleteAddressesByAddressId(Long addressId);
}
