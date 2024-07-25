package com.mybatis.mybatisprojectapp.Category;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements ICategory {
    private Long id;
    private String name;
}
