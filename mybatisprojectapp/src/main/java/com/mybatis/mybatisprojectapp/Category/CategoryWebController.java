package com.mybatis.mybatisprojectapp.Category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ctweb")
public class CategoryWebController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("")
    public String indexHome(){
        return "index";
    }

    @GetMapping("/oldhtml/category_old")
    public String categoryOld(Model model){
        try {
            List<ICategory> allList = this.categoryService.getAllList();
            model.addAttribute("itemList", allList);
        } catch (Exception ex){
            log.error(ex.toString());
        }
        return "oldhtml/category_old";
    }

    @PostMapping("/oldhtml/category_old_act")
    public String categoryOldAct(@ModelAttribute CategoryDto dto, Model model){
        try{
            if ( dto == null || dto.getName() == null || dto.getName().isEmpty()){
                return "redirect:category_old";
            }
            this.categoryService.insert(dto);
            model.addAttribute("categoryDto", dto);
        } catch (Exception ex){
            log.error(ex.toString());
            return "oldhtml/category_old";
        }
        return "redirect:category_old";
    }

    @GetMapping("/oldhtml/category_old_view")
    public String categoryOldView(@RequestParam Long id, Model model){
        try {
            ICategory byId = this.categoryService.findById(id);
            if(byId == null){
                return "redirect:category_old";
            }
            model.addAttribute("categoryDto", byId);
        } catch (Exception ex){
            log.error(ex.toString());
        }
        return "oldhtml/category_view";
    }

    @PostMapping("/oldhtml/category_old_update")
    public String categoryOldUpdate(@ModelAttribute CategoryDto dto, Model model){
        try{
            if ( dto == null || dto.getName() == null || dto.getName().isEmpty()){
                return "redirect:category_old";
            }
            this.categoryService.update(dto.getId(), dto);
        } catch (Exception ex){
            log.error(ex.toString());
            return "oldhtml/category_old";
        }
        return "redirect:category_old";
    }

    @GetMapping("/oldhtml/category_old_del")
    public String categoryOldDelete(@RequestParam Long id){
        try {
            if(id == null || id <=0 ){
                return "redirect:category_old";
            }
            ICategory byId = this.categoryService.findById(id);
            if(byId == null){
                return "redirect:category_old";
            }
            this.categoryService.remove(id);
        } catch (Exception ex){
            log.error(ex.toString());
        }
        return "redirect:category_old";
    }
}
