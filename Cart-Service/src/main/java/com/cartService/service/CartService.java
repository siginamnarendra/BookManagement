package com.cartService.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartService.client.BookClient;
import com.cartService.dao.CartItemsRepository;
import com.cartService.dao.CartRepository;
import com.cartService.dto.Book;
import com.cartService.entity.Cart;
import com.cartService.entity.CartItems;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemsRepository cartitemsRepository;
	@Autowired
	private BookClient bookClient;

//	public Cart addToCart(Cart cart) {
//		try {
//
//			Cart crt = getCartById(cart.getCustomerId());
//			crt.getCartItems().addAll(cart.getCartItems());
//
//			return cartRepository.save(crt);
//		}
//
//		catch (Exception exception) {
//
//			return cartRepository.save(cart);
//		}
//	}

	public String saveToCart(int bookId, int quantity, String customerId) {
		try {
			Cart crt = getCartById(customerId);
			Book book = bookClient.getBookById(bookId);
			if (isBookAvailabe(book.getBookStockQuantity(), quantity)) {
				CartItems cartItems = new CartItems();
				cartItems.setBookId(book.getBookId());
				cartItems.setBookPrice(book.getBookPrice());
				cartItems.setBookTitle(book.getBookTitle());
				cartItems.setQuantity(quantity);
				cartItems.setCart(crt);
				crt.getCartItems().add(cartItems);
				cartRepository.save(crt);
				return "item added to cart";
			} else {
				try {
					throw new RuntimeException();
				} catch (Exception ex) {
					return "quantitynow available! available quantity is =" + book.getBookStockQuantity();
				}
			}

		} catch (Exception ex) {
			int bookQuantity = bookClient.checkBookQuantity(bookId);
			if (isBookAvailabe(bookQuantity, quantity)) {

				Book book = bookClient.getBookById(bookId);
				Cart cart = new Cart();
				cart.setCustomerId(customerId);
				CartItems cartItems = new CartItems();
				cartItems.setBookId(book.getBookId());
				cartItems.setBookPrice(book.getBookPrice());
				cartItems.setBookTitle(book.getBookTitle());
				cart.getCartItems().add(cartItems);
				cartItems.setCart(cart);
				cartItems.setQuantity(quantity);

				cartRepository.save(cart);
				// cart.se
				return "item added to cart";
			} else {
				try {
					throw new RuntimeException();
				} catch (RuntimeException exe) {
					System.out.println("please select the <= available book quantity");
					return null;
				}

			}
		}

	}

	public boolean isBookAvailabe(int availableQuantity1, int orderedQuantity) {
		return availableQuantity1 >= orderedQuantity;
	}

	public Cart getCartById(String customerId) {
		return cartRepository.findByCustomerId(customerId);
	}

	public String deleteFromCart(int cartItemId) {
		cartitemsRepository.deleteById(cartItemId);
		return "deleted id" + cartItemId;
	}

	public Cart getCartItems(String customerId) {
		Cart cart = new Cart();
		try {
			cart = cartRepository.findById(customerId).get();
			return cart;
		} catch (RuntimeException e) {
			return cart;
		}

	}

}
