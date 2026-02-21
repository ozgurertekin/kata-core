package org.haiilo.controller;

import org.haiilo.controller.mapper.ProductMapper;
import org.haiilo.datatransferobject.ProductDTO;
import org.haiilo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll() {
        return ProductMapper.makeProductDTOList(productService.findAll());
    }
}
