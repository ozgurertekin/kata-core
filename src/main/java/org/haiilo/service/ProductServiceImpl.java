package org.haiilo.service;

import org.haiilo.dataaccessobject.ProductRepository;
import org.haiilo.domainobject.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return  productRepository.findAll();
    }
}
