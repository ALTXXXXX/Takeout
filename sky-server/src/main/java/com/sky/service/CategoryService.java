package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

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
}
