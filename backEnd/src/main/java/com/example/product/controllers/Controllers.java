package com.example.product.controllers;

import com.example.product.entiy.Product;
import com.example.product.service.IProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/product")
public class Controllers {

    @Autowired
    private IProductService iProductService;

    @GetMapping()
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> products = iProductService.findAll();
        if (!products.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable("id") Long id) {
        Optional<Product> product = iProductService.findById(id);
        if (!product.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product productCreate = iProductService.save(product);
        return  new ResponseEntity<>(productCreate, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Product>> delete(@PathVariable("id") Long id) {
        Optional<Product> product = iProductService.findById(id);
       if (!product.isPresent()) {
           new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       iProductService.delete(id);
       return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> edit(@PathVariable("id") Long id, @RequestBody Product productEdit) {
        Optional<Product> product = iProductService.findById(id);
        if(!product.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      productEdit.setId(product.get().getId());
        productEdit = iProductService.save(productEdit);
        return  new ResponseEntity<>(productEdit, HttpStatus.OK);
    }

    @GetMapping("/cate/{id}")
    public ResponseEntity<Iterable<Product>> findByCate(@PathVariable("id") Long id) {
        Iterable<Product> products = iProductService.findByCategory(id);
        if(!products.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
