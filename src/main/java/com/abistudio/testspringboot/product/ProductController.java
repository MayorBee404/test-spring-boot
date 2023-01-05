package com.abistudio.testspringboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public List<Product> listAll(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<Optional<Product>> product (@PathVariable("id") Integer id){
        if (productRepository.findById(id).isPresent()){
            return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Product> createNewProduct(@RequestBody @Valid Product newProductData){
        Product saveProduct = productRepository.save(newProductData);
        URI uri = URI.create("/product/"+saveProduct.getId());
        return ResponseEntity.created(uri).body(saveProduct);
    }
}
