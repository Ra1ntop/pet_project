package com.ra1ntest.repository.cart;

import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.cart.CartEntry;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import com.ra1ntest.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartEntryRepository extends BaseRepository<CartEntry> {

    List<CartEntry> findByCart(Cart cart);

    Optional<CartEntry> findByProductVariant(ProductVariant productVariant);
}
