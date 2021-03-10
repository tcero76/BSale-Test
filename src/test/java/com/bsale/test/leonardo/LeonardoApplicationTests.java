package com.bsale.test.leonardo;

import com.bsale.test.leonardo.payload.ResProducts;
import com.bsale.test.leonardo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeonardoApplication.class)
public class LeonardoApplicationTests {

	@Autowired
	private ProductService productService;

	@Test
	public void findAll() {
		List<ResProducts> products = productService.findAll();
		System.out.printf("Número de productos encontrados: %d%n", products.size());
		assert(products.size()==57);
	}

	@Test
	public void findByName() {
		List<ResProducts> products = productService.findByName("RON");
		System.out.printf("Número de Ron encontrados: %d%n", products.size());
		assert(products.size()==13);
	}

	@Test
	public void findByNameAndPrice() {
		List<ResProducts> products = productService.findByNameAndByPrice("RON", 5000f, 10000f);
		System.out.printf("Número de Ron encontrados entre 5000 y 10.000: %d%n", products.size());
		assert(products.size()==4);
	}

	@Test
	public void findByPrice() {
		List<ResProducts> products = productService.findByPrice(5000f,10000f);
		System.out.printf("Número de productos encontrados entre 5.000 y 10.000: %d%n", products.size());
		assert(products.size()==13);
	}

	@Test
	public void findByNameAndMinPrice() {
		List<ResProducts> products = productService.findByNameAndByMinPrice("RON",10000f);
		System.out.printf("Número de RON encontrados con mínimo 10.000: %d%n", products.size());
		assert(products.size()==1);
	}

	@Test
	public void findByMinPrice() {
		List<ResProducts> products = productService.findByMinPrice(10000f);
		System.out.printf("Número de productos encontrados con mínimo 10.000: %d%n", products.size());
		assert(products.size()==4);
	}

}
