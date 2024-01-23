package com.ra1ntest.service.cart.impl;

import com.ra1ntest.exception.CartEntryNotFoundException;
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

import java.math.BigDecimal;
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
        System.out.println("cart.getId() = " + cart.getId());
        List<CartEntry> allByCartId = cartEntryRepository.findAllByCartId(cart.getId());
        if (allByCartId.isEmpty()) {
            CartEntry cartEntry = null;
            cartEntry = new CartEntry();
            cartEntry.setCart(cart);
            cartEntry.setProductVariant(productVariant);
            cartEntry.setQuantity(quantity);
            System.out.println("cartEntry = " + cartEntry);
            cartEntry = cartEntryRepository.save(cartEntry);
        }

        if (!allByCartId.isEmpty()) {
            Optional<CartEntry> optionalCartEntry = cartEntryRepository.findByProductVariantAndCartId(productVariant, cart.getId());
            System.out.println("cart = " + cart.getId());
            CartEntry cartEntry = null;
            if (optionalCartEntry.isEmpty()) {
                cartEntry = new CartEntry();
                cartEntry.setCart(cart);
                cartEntry.setProductVariant(productVariant);
                cartEntry.setQuantity(quantity);
                BigDecimal productVariantPrice = productVariant.getPrice();
                BigDecimal totalPriceOfProductVariant = productVariantPrice.multiply(BigDecimal.valueOf(quantity));
                cartEntry.setPrice(totalPriceOfProductVariant);
                cartEntry = cartEntryRepository.save(cartEntry);
            } else {
                cartEntry = optionalCartEntry.get();
                int currentQuantity = cartEntry.getQuantity();
                currentQuantity = currentQuantity + quantity;
                cartEntry.setQuantity(currentQuantity);
                BigDecimal productVariantPrice = productVariant.getPrice();
                BigDecimal totalPriceOfProductVariant = productVariantPrice.multiply(BigDecimal.valueOf(currentQuantity));
                cartEntry.setPrice(totalPriceOfProductVariant);
                cartEntry = cartEntryRepository.save(cartEntry);
            }

            long total = calculateTotal(allByCartId).longValue();
            cart.setTotalPrice(total);
            cart = cartRepository.save(cart);
        }

    }

    private BigDecimal calculateTotal(List<CartEntry> cartEntries) {
        return cartEntries.stream()
                .map(CartEntry::getPrice) // Получаем все цены
                .reduce(BigDecimal.ZERO,
                        BigDecimal::add); // Складываем все цены
    }

    @Override
    public Integer findProductQuantity(Long productVariantId) {
        CartEntry cartEntry = cartEntryRepository.findByProductVariantIdAndCartId(productVariantId, getCart().getId());
        if (cartEntry != null) {
            return cartEntry.getQuantity();
        }
        throw new CartEntryNotFoundException("cartEntryRepository return null");
    }

    private Cart getCart() {
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
    public void deleteProductFromCart(Long productVariantId) {
        Cart cart = getCart();
        ProductVariant productVariant = productVariantRepository
                .findById(productVariantId)
                .orElseThrow(() -> new EntityNotFoundException("Product not founded"));
        System.out.println("cart.getId() = " + cart.getId());
        List<CartEntry> allByCartId = cartEntryRepository.findAllByCartId(cart.getId());
        if (allByCartId.isEmpty()) {
        }
        if (!allByCartId.isEmpty()) {
            Optional<CartEntry> optionalCartEntry = cartEntryRepository.findByProductVariantAndCartId(productVariant, cart.getId());
            System.out.println("cart = " + cart.getId());
            if (optionalCartEntry.isEmpty()) {
            } else {
                Long id = cartEntryRepository.findById(optionalCartEntry.get().getId()).get().getId();
                cartEntryRepository.deleteById(id);
            }
        }


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
