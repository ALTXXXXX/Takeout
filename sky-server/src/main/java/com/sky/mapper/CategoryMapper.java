package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user) VALUES "+
    "(#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void add(Category category);


    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    //更新
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    //根据id删除分类
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);


    List<Category> queryByType(Integer type);
}
