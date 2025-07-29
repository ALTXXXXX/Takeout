package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    /**
     * 新增地址
     * @param addressBook
     */
    void addAddress(AddressBook addressBook);

    /**
     * 获取当前用户所有地址信息
     * @return
     */
    List<AddressBook> getAllAddress();

    /**
     * 获取默认地址信息
     * @return
     */
    AddressBook getDefaultAddress();

    /**
     * 根据id设置默认地址
     * @param id
     */
    void setDefaultAddressById(Long id);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    AddressBook getAddressById(Long id);

    /**
     * 根据id修改地址
     * @param addressBook
     */
    void updateById(AddressBook addressBook);

    /**
     * 根据id删除地址
     * @param id
     */
    void deleteById(Long id);
}
