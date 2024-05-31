package az.spring.elasticsearch.controller;

import az.spring.elasticsearch.dto.request.ProductRequest;
import az.spring.elasticsearch.dto.response.ProductResponse;
import az.spring.elasticsearch.dto.response.ProductResponseList;
import az.spring.elasticsearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ProductResponseList> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<ProductResponseList> searchHits(@PathVariable(name = "name") String name) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchHits(name));
    }

}