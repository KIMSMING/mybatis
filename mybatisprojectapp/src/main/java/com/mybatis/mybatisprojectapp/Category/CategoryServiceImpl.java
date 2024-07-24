package com.mybatis.mybatisprojectapp.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private CategoryMybatisMapper catecoryMybatisMapper;

    @Override
    public ICategory findById(Long id) {
        if ( id == null || id <= 0 ){
            return null;
        }
        CategoryDto dto = this.catecoryMybatisMapper.findById(id);
        return dto;
    }

    @Override
    public ICategory findByName(String name) {
        if ( name == null || name.isEmpty() ){
            return null;
        }
        CategoryDto dto= this.catecoryMybatisMapper.findByName(name);
        return dto;
    }

    @Override
    public List<ICategory> getAllList() {
        List<ICategory> list = this.getICategoryList(
                this.catecoryMybatisMapper.findAll()
        );
        return list;
    }

    private List<ICategory> getICategoryList(List<CategoryDto> list) {
        if( list == null || list.size() <= 0 ) {
            return new ArrayList<>();
        }
        List<ICategory> result = list.stream()
                .map(item -> (ICategory)item)
                .toList();
        return result;
    }

    private boolean isValidInsert(ICategory category) {
        if ( category == null ){
            return false;
        }else if ( category.getName() == null || category.getName().isEmpty()){
            return false;
//       else return category.getName() != null && !category.getName().isEmpty();
        }
        return true;
    }

    @Override
    public ICategory insert(ICategory category) throws Exception{
        if ( !this.isValidInsert(category)){
            return null;
        }
        CategoryDto dto = new CategoryDto();
        dto.copyFields(category);
        dto.setId(0L);
        this.catecoryMybatisMapper.insert(dto);
        return dto;
    }

    @Override
    public boolean remove(Long id) throws Exception {
        ICategory find = this.findById(id);
        if ( find == null ){
            return false;
        }
        this.catecoryMybatisMapper.deleteById(id);
        return true;
    }

    @Override
    public ICategory update(Long id, ICategory category) throws Exception {
        ICategory find = this.findById(id);
        if ( find == null ){
            return null;
        }
        find.copyFields(category);
        this.catecoryMybatisMapper.update((CategoryDto) find);
        return find;
    }

    @Override
    public List<ICategory> findAllByNameContains(String name) {
        if ( name == null || name.isEmpty() ){
            return null;
        }
        List<ICategory> list = this.getICategoryList(
                this.catecoryMybatisMapper.findAllByNameContains(name)
        );
        return list;
    }
}
