package com.example.springboot.controller;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name = "PRODUCT",description = "api para controle de produtos")
public class ProductController {
    @Autowired
    ProductRepository ProductRepository;
    @Operation(summary = "Serviço para cadastro de produtos.", description = "PostProducts")
    @PostMapping("/products")
    public ResponseEntity<ProductModel> Post(@RequestBody @Valid ProductRecordDto productRecordDto){
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductRepository.save(productModel));
    }

    @Operation(summary = "Serviço para listagem de produtos.", description = "GetAllProducts")
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> GetAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ProductRepository.findAll());
    }

    @Operation(summary = "Serviço para busca de um unico produto.", description = "GetOneProducts")
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> GetOne(@PathVariable(value = "id")UUID id){
        Optional<ProductModel> product1 = ProductRepository.findById(id);
        if (product1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product1.get());
    }
    @Operation(summary = "Serviço para atualizar um produto.", description = "PutProducts")
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> Put(@PathVariable(value = "id")UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){
        Optional<ProductModel> product1 = ProductRepository.findById(id);
        if (product1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
            var productModel = product1.get();
            BeanUtils.copyProperties(productRecordDto, productModel);
            return ResponseEntity.status(HttpStatus.OK).body(product1.get());
    }
}
