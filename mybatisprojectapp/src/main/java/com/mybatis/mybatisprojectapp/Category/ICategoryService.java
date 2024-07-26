package com.mybatis.mybatisprojectapp.Category;


import com.mybatis.mybatisprojectapp.ICommonService;

import java.util.List;

public interface ICategoryService<T> extends ICommonService<T> {
    ICategory findByName(String name);
    List<ICategory> findAllByNameContains(SearchCategoryDto dto);
    int countAllByNameContains(SearchCategoryDto searchCategoryDto);
}
