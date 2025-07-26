package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    //批量添加口味
    void addBatch(List<DishFlavor> flavors);

    //根据菜品id删除口味
    @Delete("delete from dish_flavor where dish_id =  #{dishId}")
    void deleteByDishId(Long dishId);

    //根据菜品id批量删除口味
    void deleteByDishIds(List<Long> ids);

    /**
     * 根据菜品id查询所有口味
     * @param dishId
     * @return
     */
    List<DishFlavor> getByDishId(Long dishId);

    /**
     * 根据菜品id修改口味
     * @param id
     */
    @Update("update dish_flavor set name=#{name},value=#{value} where dish_id=#{id}")
    void updateByDishId(Long id);
}

