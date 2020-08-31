package com.application;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProductDAO;
import com.dto.Product;

public class MyMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		ProductDAO pdao = (ProductDAO)context.getBean("pdao");
		
		Product p1 = new Product();
		p1.setId(1);
		p1.setName("Printer");
		p1.setPrice(25000);
		
		if(pdao.insertProduct(p1))
			System.out.println("Inserted");
		else
			System.out.println("Insertion Failed");
		
//		Product p1 = new Product();
//		p1.setId(1);
//		p1.setName("Laptop");
//		p1.setPrice(30000);
//		
//		if(pdao.updateProduct(p1))
//			System.out.println("Updated");
//		else
//			System.out.println("Updation Failed");
		
		
//		if(pdao.deleteProduct(3))
//			System.out.println("Deleted");
//		else
//			System.out.println("Deletion Failed");
		
//		Product p = pdao.searchProduct(7);
//		
//		if(p!=null) {
//			System.out.println(p.getId() + " : " + p.getName() + " : " + p.getPrice());
//		}
//		else {
//			System.out.println("Not Found");
//		}
		
		
//		List<Product> lst = pdao.getAllProduct();
//		
//		if(lst.isEmpty()) {
//			System.out.println("No Products");
//		}
//		else {
//			for(Product pr1:lst)
//			{
//				System.out.println(pr1.getId() + " : " + pr1.getName() + " : " + pr1.getPrice());
//			}
//		}
		context.close();
	}

}
