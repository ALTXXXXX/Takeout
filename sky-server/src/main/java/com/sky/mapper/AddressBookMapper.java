package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    /**
     * 新增地址
     * @param addressBook
     */
    void insertAddress(AddressBook addressBook);

    /**
     * 根据用户id查询所有的地址信息
     * @param userId
     * @return
     */
    @Select("select * from address_book where user_id = #{userId}")
    List<AddressBook> list(Long userId);

    /**获取默认地址信息
     * @param
     * @return
     */
    @Select("select * from address_book where is_default = 1 and user_id = #{userId}")
    AddressBook getDefaultAddress(Long userId);

    /**
     * 设置默认地址
     * @param id
     */
    @Update("update address_book set is_default = 1 where id = #{id}")
    void setDefaultById(Long id);

    /**
     * 根据id查询出当前用户Id
     * @param id
     * @return
     */


    /**
     * 将当前userId设置成非默认地址
     * @param userId
     */
    @Update("update address_book set is_default = 0 where user_id = #{userId}")
    void setUnDefaultByUserId(Long userId);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Select("select * from address_book where id = #{id}")
    AddressBook getById(Long id);

    /**
     * 根据id修改地址
     * @param addressBook
     */
    void updateById(AddressBook addressBook);

    /**
     * 根据id删除地址
     * @param id
     */
    @Delete("delete from address_book where id = #{id}")
    void deleteById(Long id);
}
