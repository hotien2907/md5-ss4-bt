package com.ra.controller;
import com.ra.entity.Category;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public ResponseEntity <List<Category>> getAll(){

        List<Category> categories=  categoryService.findAll();
        if(categories.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <Category> findById(@PathVariable ("id") Integer id){
          Category category = categoryService.findById(id);

        if(category==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category,HttpStatus.OK);
//        if(category==null){
//            return  ResponseEntity.status(500).body("loi 500");
//        }
//        return  ResponseEntity.status(200).body(category);
    }


    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category categoryNew = categoryService.save(category);
                if(categoryNew ==null){
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        return new ResponseEntity<>(categoryNew,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> doUpdate(@RequestBody Category category,@PathVariable Integer id){
        if(categoryService.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         Category category1= categoryService.save(category);
        return new ResponseEntity<>(category1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Integer id){
        Category category = categoryService.findById(id);
        if(category!=null){
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}