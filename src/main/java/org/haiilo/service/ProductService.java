package org.haiilo.service;

import org.haiilo.domainobject.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
}
