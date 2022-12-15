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

    //TODO 2. Buat sebuah fungsi/API mcroservice yang berfungsi memberikan data untuk sebuah produk saja, dengan spesifikasi sbb:
    //TODO 2a. Path untuk mengakses produk tersebut adalah /produk/id dimana id akan digantikan oleh nomor ID untuk produk yang diinginkan
    @GetMapping("{id}")
    public ResponseEntity<Optional<Product>> Id(@PathVariable("id")Integer id){
        if(productRepository.findById(id).isPresent()){
            //TODO 2b. Jika produk ditemukan, maka API tersebut mengembalikan HTTP 200 (OK) dengan body berisi data produk yang ditemukan
            return ResponseEntity.ok(productRepository.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createNewProduct(@RequestBody @Valid Product newProductData){
        try {
            Product saveProduct = productRepository.save(newProductData);
            //TODO 3. Modifikasi HTTP Response yang dikembalikan aplikasi pada saat create produk baru agar mengandung URL produk sesuai nomor 2 diatas.
            URI newProductURI = URI.create("/product/" + saveProduct.getId());
            return ResponseEntity.created(newProductURI).body(saveProduct);
        }catch (Exception e){
            //TODO 2c. Jika produk tidak ditemukan, maka API tersebut mengembalikan HTTP Response 404 (Not Found) dengan body kosong
            return ResponseEntity.notFound().build();
        }

    }
}
