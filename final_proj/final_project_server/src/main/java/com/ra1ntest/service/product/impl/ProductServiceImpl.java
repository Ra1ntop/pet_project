package com.ra1ntest.service.product.impl;

import com.ra1ntest.exception.EntityNotFoundException;
import com.ra1ntest.exception.EntityUnexistsException;
import com.ra1ntest.exception.EntityUnexistsName;
import com.ra1ntest.exception.NonValidFieldDataException;
import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.repository.product.ProductRepository;
import com.ra1ntest.service.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ra1ntest.util.ExceptionUtil.*;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void create(Product product) {
        if (product == null) {
            throw new EntityUnexistsException(PRODUCT_ENTITY_UNEXISTS_EXCEPTION);
        }
        if (product.getName() == null) {
            throw new EntityUnexistsName(PRODUCT_ENTITY_UNEXISTS_NAME_EXCEPTION);
        }
        productRepository.save(product);
    }

    @Override
    public boolean isProductExists(Long id) {
        if (id == null) {
            return false;
        }
        if (productRepository.findById(id).isEmpty()) {
            return false;
        }
        if (productRepository.findById(id).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        if (id == null) {
            throw new NonValidFieldDataException(NON_VALID_FIELD_DATA_EXCEPTION);
        }
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
