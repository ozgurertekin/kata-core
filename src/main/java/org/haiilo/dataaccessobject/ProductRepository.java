package org.haiilo.dataaccessobject;

import org.haiilo.domainobject.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
