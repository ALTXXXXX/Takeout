package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

   @Autowired
   private AddressBookMapper addressBookMapper;


    @Override
    public void addAddress(AddressBook addressBook) {
        Long userId = BaseContext.getCurrentId();
        addressBook.setUserId(userId);
        addressBook.setIsDefault(0);
        addressBookMapper.insertAddress(addressBook);
    }

    @Override
    public List<AddressBook> getAllAddress() {
        Long userId = BaseContext.getCurrentId();
       return addressBookMapper.list(userId);
    }

    @Override
    public AddressBook getDefaultAddress() {
        Long userId = BaseContext.getCurrentId();
        AddressBook addressBook = addressBookMapper.getDefaultAddress(userId);
       return addressBook;
    }

    @Override
    public void setDefaultAddressById(Long id) {
        //将当前用户其他id设置成非默认
        Long userId = BaseContext.getCurrentId();
        addressBookMapper.setUnDefaultByUserId(userId);
        //将当前id设置成默认
        addressBookMapper.setDefaultById(id);



    }

    @Override
    public AddressBook getAddressById(Long id) {
       return addressBookMapper.getById(id);
    }

    @Override
    public void updateById(AddressBook addressBook) {
        addressBookMapper.updateById(addressBook);
    }

    @Override
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);

    }
}
