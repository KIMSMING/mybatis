package com.mybatis.mybatisprojectapp.Category;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CatecoryMybatisMapper {
    void insert(CategoryDto dto);

    CategoryDto findById(long id);

    CategoryDto findByName(String name);

    List<CategoryDto> findAll();

    void deleteById(Long id);

    void update(CategoryDto dto);

    List<CategoryDto> findAllByNameContains(String name);
}
