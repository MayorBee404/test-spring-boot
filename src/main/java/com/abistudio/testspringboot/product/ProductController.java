package com.abistudio.testspringboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/list")
    public List<Product> listAll(){
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Product>> Id(@PathVariable("id")Integer id){
        if(productRepository.findById(id).isPresent()){
            return ResponseEntity.ok(productRepository.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createNewProduct(@RequestBody @Valid Product newProductData){
        try {
            Product saveProduct = productRepository.save(newProductData);
            URI newProductURI = URI.create("/product/" + saveProduct.getId());
            return ResponseEntity.created(newProductURI).body(saveProduct);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }
}
