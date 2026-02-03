package com.example.demo.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.UserEntity;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CartService {

    private final CartItemRepo cartItemRepo;
	

	@Autowired
	private CartRepo cartRepo;

	
	@Autowired
	private GetCurrentUserService authService;
	


    CartService(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }
	
	


	
		private CartEntity getorCreateCart() {
			
			UserEntity user =authService.getUserName();
			
			return cartRepo.findByUser(user).orElseGet(()->{
				
				CartEntity cart =new CartEntity();
				cart.setUser(user);
				return cartRepo.save(cart);
				
			});
			
			
			
		}
		
		void AddItem(long prodectId,int quantity) {
				
				CartEntity cart = getorCreateCart();
				
				
				CartItemEntity item = cartItemRepo
			                .findByCartAndProductId(cart, prodectId)
			                .orElse(null);
				
				   if (item != null) {
			            // ✅ UPDATE quantity
			            item.setQuantity(item.getQuantity() + quantity);
			            cartItemRepo.save(item);
			        } else {
			            // ✅ CREATE new row only if product not present
			        	CartItemEntity newItem = new CartItemEntity();
			            newItem.setCart(cart);
			            newItem.setProduct_Id(prodectId);
			            newItem.setQuantity(quantity);
			            cartItemRepo.save(newItem);
			        }
				
//				CartItemEntity item=new CartItemEntity();
//				item.setCart(cart);
//				item.setProduct_Id(prodectId);
//				item.setQuantity(quantity);
//				
//				
//				cartItemRepo.save(item);
				
		}
		
		List<CartItemEntity> listItem(){
			
			return cartItemRepo.findByCart(getorCreateCart());
			
		}
		
		void removeItem(Long itemid) {
			cartItemRepo.deleteById(itemid);
		}
}
