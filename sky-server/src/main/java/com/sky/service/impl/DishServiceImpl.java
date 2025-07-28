package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.Setmeal;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;
    @Autowired
    DishFlavorMapper dishFlavorMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;
    @Autowired
    private SetmealMapper setmealMapper;


    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
       //添加一个菜品
        dishMapper.add(dish);
        //或许菜品生成的id值
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
         if(flavors!=null&&flavors.size()>0){
             //添加当前菜品的n个口味
             flavors.forEach(dishFlavor->{
                 dishFlavor.setDishId(dishId);
             });
             dishFlavorMapper.addBatch(flavors);
        }

    }

    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
     Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
       return new PageResult(page.getTotal(),page.getResult());

    }

    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
        //是否能够删除--是否存在起售中的菜品
        for(Long id:ids){
          Dish dish = dishMapper.getById(id);
            if(dish.getStatus()== StatusConstant.ENABLE){
                //菜品处于起售中，不能删除
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        //当前菜品是否被套餐关联
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if(setmealIds!=null&&setmealIds.size()>0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);

        }
        //删除菜品
//        for(Long id:ids){
//            dishMapper.deleteById(id);
//            //删除菜品对应的口味
//            dishFlavorMapper.deleteByDishId(id);
//        }
        //delete from dish where id in (?,?,?)
        //优化：批量删除菜品和口味
        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteByDishIds(ids);


    }

    @Override
    public DishVO queryWithFlavorsById(Long id) {
        //根据id查询菜品
        Dish dish = dishMapper.getById(id);
        //根据id查询相应的菜品口味
        List<DishFlavor> dishFlavor = dishFlavorMapper.getByDishId(id);
        //封装vo并返回
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(dishFlavor);
       return dishVO;
    }

    @Transactional
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        //获取菜品
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        //修改菜品基本信息
        dishMapper.update(dish);

        //删除菜品原有的口味
        Long dishId = dish.getId();
        dishFlavorMapper.deleteByDishId(dishId);

        //批量插入对应口味
        List<DishFlavor>  dishFlavors = dishDTO.getFlavors();
       if(dishFlavors!=null&&dishFlavors.size()>0){
           dishFlavors.forEach(dishFlavor->{
               dishFlavor.setDishId(dishId);
           });
           dishFlavorMapper.addBatch(dishFlavors);
       }

    }

    @Override
    public List<Dish> getByCategoryId(Long categoryId) {
        //根据种类id查询菜品

      List<Dish> dishes = dishMapper.getByCategoryId(categoryId);
        return dishes;
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Dish dish = new  Dish();
        dish.setStatus(status);
        dish.setId(id);
        dishMapper.update(dish);
        //如果是停售操作，需要将相关联的套餐也停售
        if(status == StatusConstant.DISABLE){
            List<Long> dishIds = new  ArrayList<>();
            dishIds.add(dish.getId());
            List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(dishIds);
            if(setmealIds != null && setmealIds.size()>0){
                setmealIds.forEach(setmealId -> {
                    Setmeal setmeal = Setmeal
                            .builder()
                            .id(setmealId)
                            .status(StatusConstant.DISABLE)
                            .build();
                    setmealMapper.update(setmeal);


                });
            }
        }




    }

    @Override
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishes = dishMapper.list(dish);
        List<DishVO> dishVOList = new ArrayList<>();
        for(Dish d:dishes){
            //根据菜品id查询口味
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);
            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());
            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }
        return dishVOList;
    }
}
