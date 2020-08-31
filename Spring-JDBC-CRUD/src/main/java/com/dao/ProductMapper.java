package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.Product;

public class ProductMapper implements RowMapper<Product>{
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product prd = new Product();
		prd.setId(rs.getInt("id"));
		prd.setName(rs.getString("name"));
		prd.setPrice(rs.getInt("price"));
		return prd;
	}
	
}
