package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    //根据分类的id查询套餐数量
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    //插入套餐
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    //分页查询套餐
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    //获取套餐
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);

    /**
     * 根据批量删除套餐
     * @param ids
     */
    void deleteBatchByIds(List<Long> ids);

    /**
     * 更新套餐数据
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 根据种类id查询套餐
     * @return
     */
    @Select("select * from setmeal where category_id = #{categoryId}")
    List<Setmeal> getByCategoryId(Long categoryId);

    List<Setmeal> list(Setmeal setmeal);
}
