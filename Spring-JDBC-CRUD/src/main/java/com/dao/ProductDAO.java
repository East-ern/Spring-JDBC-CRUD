package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.dto.Product;

public class ProductDAO {
	private JdbcTemplate mytemplate;

	public ProductDAO() {
		super();
	}

	public JdbcTemplate getMytemplate() {
		return mytemplate;
	}

	public void setMytemplate(JdbcTemplate mytemplate) {
		this.mytemplate = mytemplate;
	}
	
	public boolean insertProduct(Product prd) {
		boolean b= false;
		
		String query = "INSERT INTO product(id,name,price) values("+prd.getId()+",'"+prd.getName()+"',"+prd.getPrice()+")";
		int count = mytemplate.update(query);
		
		if(count>0)
		{
			b= true;
		}
		
		return b;
	}
	
	public boolean saveProduct(Product prd) {
		boolean b= false;
		
		String query = "INSERT INTO product(id,name,price) values(?,?,?)";
		int count = mytemplate.execute(query, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, prd.getId());
				ps.setString(2, prd.getName());
				ps.setInt(3, prd.getPrice());
				
				int x = ps.executeUpdate();
				
				return x;
			}
		});
		
		if(count>0)
		{
			b= true;
		}
		
		return b;
	}
	
	public boolean deleteProduct(int pid) {
		boolean b = false;
		
		String query = "DELETE FROM product WHERE id=? ";
		
		int count = mytemplate.execute(query, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, pid);
				
				int x = ps.executeUpdate();
				return x;
			}
		});
		
		if(count>0)
		{
			b= true;
		}
		
		return b;
	}
	
	public boolean updateProduct(Product prd) {
		boolean b = false;
		
		String query = "UPDATE product SET name = ?, price = ? WHERE id=? ";
		
		int count = mytemplate.execute(query, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, prd.getName());
				ps.setInt(2, prd.getPrice());
				ps.setInt(3, prd.getId());
				
				int x = ps.executeUpdate();
				return x;
			}
		});
		
		if(count>0)
		{
			b= true;
		}
		
		return b;
	}
	
	public Product searchProduct(int pid) {
		Product prd = null;
		
		String query = "SELECT * FROM product where id=?";
		
		prd = mytemplate.execute(query , new PreparedStatementCallback<Product>() {
			@Override
			public Product doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				
				ps.setInt(1, pid);
				
				ResultSet rs = ps.executeQuery();
				Product p = new Product();
				if(rs.isBeforeFirst()) {
					rs.next();
					
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("name"));
					p.setPrice(rs.getInt("price"));
				}
				
				return p;
			}
		});
		
		return prd;
	}
	
	public List<Product> getAllProduct()
	{
		List<Product> lst = new ArrayList<Product>();
		
		String query = "select * from product";
		
		lst = mytemplate.query(query, new ResultSetExtractor<List<Product>>() {
			@Override
			public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Product> mylist = new ArrayList<>();
				
				while(rs.next()) {
					Product p = new Product();
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("name"));
					p.setPrice(rs.getInt("price"));
					mylist.add(p);
				}
				
				return mylist;
			}
		});
		
		return lst;
	}
	
	public List<Product> showAllProduct()
	{
		List<Product> lst = new ArrayList<Product>();
		
		String query = "select * from product";
		
		lst = mytemplate.query(query, new ProductMapper());
		
		return lst;
	}
}
