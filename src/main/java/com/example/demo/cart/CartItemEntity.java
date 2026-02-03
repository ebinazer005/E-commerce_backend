package com.example.demo.cart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItem")
public class CartItemEntity {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
			
				
		@ManyToOne(optional = false)
		@JoinColumn(name ="cart_id")
		private CartEntity cart;
		
		private Long productId;
		private int quantity;
		
		
		public Long getId() {
			return id;
		}
		
		public CartEntity getCart() {
			return cart;
		}
		public void setCart(CartEntity cart) {
			this.cart = cart;
		}
		public Long getProduct_Id() {
			return productId;
		}
		public void setProduct_Id(Long product_Id) {
			this.productId = product_Id;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
		
}
