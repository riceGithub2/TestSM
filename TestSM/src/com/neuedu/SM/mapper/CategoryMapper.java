package com.neuedu.SM.mapper;

import java.util.List;

import com.neuedu.SM.model.Category;

public interface CategoryMapper {
	public  List<Category> list();
	public Category get(int id);
	
	public int add(Category c);
	public void delete(int id);
	public int update(Category c);
	
	
}
