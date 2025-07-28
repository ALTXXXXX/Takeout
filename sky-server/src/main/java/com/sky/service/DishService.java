package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DishService {
 /**
  * 添加菜品
  * @param dishDTO
  */
 void saveWithFlavor(DishDTO dishDTO);

 /**
  * 分页查询菜品
  * @param dishPageQueryDTO
  * @return
  */
 PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

 /**
  * 菜品批量删除
  * @param ids
  */
   void deleteBatch(List<Long> ids);


 /**
  * 根据id查询菜品以及关联的口味
  * @param id
  * @return
  */
    DishVO queryWithFlavorsById(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> getByCategoryId(Long categoryId);

    /**
     * 菜品起售停售
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 查询菜品和对应的口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
