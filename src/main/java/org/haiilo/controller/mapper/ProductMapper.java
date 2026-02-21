package org.haiilo.controller.mapper;

import org.haiilo.datatransferobject.ProductDTO;
import org.haiilo.domainobject.Product;

import java.util.Collection;
import java.util.List;

public class ProductMapper {
    public static ProductDTO makeProductDTO(Product product)
    {
        return new ProductDTO(product.getId(), product.getName(), product.getPriceAmount());
    }


    public static List<ProductDTO> makeProductDTOList(Collection<Product> products)
    {
        return products.stream()
                .map(ProductMapper::makeProductDTO)
                .toList();
    }
}
