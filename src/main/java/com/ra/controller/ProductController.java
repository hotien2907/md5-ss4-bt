package com.ra.controller;
import com.ra.entity.Product;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){

        List<Product> products=  productService.findAll();
        if(products.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <Product> findById(@PathVariable("id") Integer id){
        Product product = productService.findById(id);

        if(product==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product productNew = productService.save(product);
        if(productNew ==null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productNew,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> doUpdate(@RequestBody Product product,@PathVariable Integer id){
        if(productService.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product1= productService.save(product);
        return new ResponseEntity<>(product1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Integer id){
        Product product = productService.findById(id);
        if(product!=null){
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
