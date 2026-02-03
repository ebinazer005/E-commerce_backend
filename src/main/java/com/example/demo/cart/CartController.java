package com.example.demo.cart;

import java.util.List;

import com.example.demo.jwt.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final AuthController authController;
	
 
	@Autowired
	private CartService cartService;

    CartController(AuthController authController) {
        this.authController = authController;
    }

	@PostMapping("/add")
	private String addtoCart(@RequestBody AddCartRequest addCartRequest) {
		cartService.AddItem(addCartRequest.getProductId() , addCartRequest.getQuantity());
		return "item add";

	}
	
	@GetMapping
	private List<CartItemEntity> viewCart(){
		
		return cartService.listItem();
	}
	
	
	@DeleteMapping("/{itemId}")
	private String deleteItem(@PathVariable Long itemId) {
		 cartService.removeItem(itemId);
		 return "item deleted";
		 
	}
}
