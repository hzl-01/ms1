package com.h.jq.mapper;

import java.util.List;
import com.h.jq.domain.Addresses;

/**
 * 地址Mapper接口
 * 
 * @author hzl
 * @date 2025-09-05
 */
public interface AddressesMapper 
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
     * 删除地址
     * 
     * @param addressId 地址主键
     * @return 结果
     */
    public int deleteAddressesByAddressId(Long addressId);

    /**
     * 批量删除地址
     * 
     * @param addressIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAddressesByAddressIds(Long[] addressIds);
}
