package org.haiilo.controller.mapper;

import org.haiilo.datatransferobject.ProductDTO;
import org.haiilo.domainobject.Product;

import java.util.Collection;
import java.util.List;

public class ProductMapper {
    public static ProductDTO makeProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPriceAmount());
    }


    public static List<ProductDTO> makeProductDTOList(Collection<Product> products) {
        return products.stream()
                .map(ProductMapper::makeProductDTO)
                .toList();
    }

    public static Product makeProduct(ProductDTO productDTO) {
        return new Product(productDTO.id(), productDTO.name(), productDTO.priceAmount());
    }

    public static List<Product> makeProductList(Collection<ProductDTO> productDTOs) {
        return productDTOs.stream()
                .map(ProductMapper::makeProduct)
                .toList();
    }
}
