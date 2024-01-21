package com.ra1ntest.service.cart.impl;

import com.ra1ntest.api.dto.response.cart.CartItemsDto;
import com.ra1ntest.api.dto.response.product.ProductPlpDto;
import com.ra1ntest.exception.EntityNotFoundException;
import com.ra1ntest.persistance.entity.cart.Cart;
import com.ra1ntest.persistance.entity.cart.CartEntry;
import com.ra1ntest.persistance.entity.product.ProductVariant;
import com.ra1ntest.persistance.entity.user.Customer;
import com.ra1ntest.repository.cart.CartEntryRepository;
import com.ra1ntest.repository.cart.CartRepository;
import com.ra1ntest.repository.product.ProductVariantRepository;
import com.ra1ntest.repository.user.CustomerRepository;
import com.ra1ntest.service.cart.CartService;
import com.ra1ntest.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartEntryRepository cartEntryRepository;
    private final CustomerRepository customerRepository;
    private final ProductVariantRepository productVariantRepository;

    @Override
    public void addProductToCart(Long productVariantId, int quantity) {
        String userName = SecurityUtil.getUserName();
        System.out.println("userName = " + userName);
        Customer customer = customerRepository
                .findByLogin(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not founded"));
        Cart cart = null;
        Optional<Cart> carts = cartRepository.findByCustomerAndActiveTrue(customer);
        if (carts.isEmpty()) {
            cart = new Cart();
            cart.setCustomer(customer);
        } else {
            cart = carts.get();
        }
        cart = cartRepository.save(cart);


        ProductVariant productVariant = productVariantRepository
                .findById(productVariantId)
                .orElseThrow(() -> new EntityNotFoundException("Product not founded"));
        CartEntry cartEntry = null;
        Optional<CartEntry> optionalCartEntry = cartEntryRepository.findByProductVariant(productVariant);
        if (optionalCartEntry.isEmpty()) {
            cartEntry = new CartEntry();
            cartEntry.setCart(cart);
            cartEntry.setProductVariant(productVariant);
            cartEntry.setQuantity(quantity);
            cartEntry = cartEntryRepository.save(cartEntry);
        } else {
            cartEntry = optionalCartEntry.get();
            int currentQuantity = cartEntry.getQuantity();
            currentQuantity = currentQuantity + quantity;
            cartEntry.setQuantity(currentQuantity);
            cartEntry = cartEntryRepository.save(cartEntry);
        }
    }

    @Override
    public void deleteProductFromCart(Long productVariantId) {

    }

    @Override
    public Cart getActive() {
        return null;
    }

    @Override
    public Cart findCart() {
        String userName = SecurityUtil.getUserName();
        System.out.println("userName = " + userName);
        Customer customer = customerRepository
                .findByLogin(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not founded"));
        Cart cart = null;
        Optional<Cart> carts = cartRepository.findByCustomerAndActiveTrue(customer);
        if (carts.isEmpty()) {
            cart = new Cart();
            cart.setCustomer(customer);
        } else {
            cart = carts.get();
        }
        cart = cartRepository.save(cart);
        return cart;
    }

    @Override
    public List<CartEntry> getEntriesByCart(Cart cart) {

        return cartEntryRepository.findByCart(cart);
    }
}
