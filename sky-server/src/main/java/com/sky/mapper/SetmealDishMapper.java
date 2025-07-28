package com.sky.mapper;

import com.sky.entity.Dish;
import com.sky.entity.SetmealDish;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {


    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    //批量插入套餐中的菜品
    void insertBatch(List<SetmealDish> setmealDishes);

    //根据套餐id获取套餐关联的菜品
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);

    /**
     * 根据套餐id批量删除关联的菜品
     * @param setmealIds
     */
    void deleteBatchBySetmealIds(List<Long> setmealIds);

    /**
     * 根据套餐id查询菜品数据
     * @param setmealId
     * @return
     */
    List<Dish> getDishBySetmealId(Long setmealId);

    List<DishItemVO> getDishItemBySetmealId(Long id);
}
