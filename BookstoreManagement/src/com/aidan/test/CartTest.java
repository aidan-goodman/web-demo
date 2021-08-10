package com.aidan.test;

import com.aidan.pojo.Cart;
import com.aidan.pojo.CartItem;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class CartTest {

    @Test
    public void testAddItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));

        System.out.println(cart);
    }

    @Test
    public void testDeleteItem() {

    }

    @Test
    public void testClear() {
    }

    @Test
    public void testUpdateCount() {
    }
}