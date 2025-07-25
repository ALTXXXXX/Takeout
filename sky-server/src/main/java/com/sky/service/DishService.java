package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface DishService {
   //添加菜品
    void saveWithFlavor(DishDTO dishDTO);

    //分页查询菜品
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    //菜品批量删除
    void deleteBatch(List<Long> ids);
}
