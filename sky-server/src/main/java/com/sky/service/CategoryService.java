package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    //添加分类
    void add(CategoryDTO categoryDTO);

    //分页查询
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    //启动，禁用分类状态
    void startOrStop(Integer status, Long id);

    //根据id删除分类
    void deleteById(Long id);

    void update(CategoryDTO categoryDTO);

    //根据类型查询分类
    List<Category> queryByType(Integer type);
}
