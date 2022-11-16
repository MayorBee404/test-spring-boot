package com.abistudio.testspringboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/list")
    public List<Product> listAll(){
        return productRepository.findAll();
    }

    @PostMapping("/create")

    public ResponseEntity<Product> createNewProduct(@RequestBody @Valid Product newProductData){

        Product saveProduct = productRepository.save(newProductData);

        URI newProductURI = URI.create("/product/" + saveProduct.getId());

        return ResponseEntity.created(newProductURI).body(saveProduct);


    }
}
