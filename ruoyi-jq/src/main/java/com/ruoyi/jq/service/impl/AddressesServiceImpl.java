package com.ruoyi.jq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jq.mapper.AddressesMapper;
import com.ruoyi.jq.domain.Addresses;
import com.ruoyi.jq.service.IAddressesService;

/**
 * 地址Service业务层处理
 * 
 * @author hzl
 * @date 2025-09-05
 */
@Service
public class AddressesServiceImpl implements IAddressesService 
{
    @Autowired
    private AddressesMapper addressesMapper;

    /**
     * 查询地址
     * 
     * @param addressId 地址主键
     * @return 地址
     */
    @Override
    public Addresses selectAddressesByAddressId(Long addressId)
    {
        return addressesMapper.selectAddressesByAddressId(addressId);
    }

    /**
     * 查询地址列表
     * 
     * @param addresses 地址
     * @return 地址
     */
    @Override
    public List<Addresses> selectAddressesList(Addresses addresses)
    {
        return addressesMapper.selectAddressesList(addresses);
    }

    /**
     * 新增地址
     * 
     * @param addresses 地址
     * @return 结果
     */
    @Override
    public int insertAddresses(Addresses addresses)
    {
        return addressesMapper.insertAddresses(addresses);
    }

    /**
     * 修改地址
     * 
     * @param addresses 地址
     * @return 结果
     */
    @Override
    public int updateAddresses(Addresses addresses)
    {
        return addressesMapper.updateAddresses(addresses);
    }

    /**
     * 批量删除地址
     * 
     * @param addressIds 需要删除的地址主键
     * @return 结果
     */
    @Override
    public int deleteAddressesByAddressIds(Long[] addressIds)
    {
        return addressesMapper.deleteAddressesByAddressIds(addressIds);
    }

    /**
     * 删除地址信息
     * 
     * @param addressId 地址主键
     * @return 结果
     */
    @Override
    public int deleteAddressesByAddressId(Long addressId)
    {
        return addressesMapper.deleteAddressesByAddressId(addressId);
    }
}
