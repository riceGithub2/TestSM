package com.neuedu.SM.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.SM.mapper.CategoryMapper;
import com.neuedu.SM.model.Category;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit 4进行测试
@ContextConfiguration("classpath:applicationContext.xml")//Spring整合JUnit4测试时，使用注解引入配置文件
public class TestSM {
	@Autowired// 注入注解
	private CategoryMapper categorymapper;
	
	
	@Test	
	@Transactional
	public void testAdd(){  //测试增加方法
		Category category = new Category();
		category.setName("newCategory");
		categorymapper.add(category);
	}
 
	@Test
	public void testList(){   //测试查询方法
		  List<Category> cs=categorymapper.list();
	        for (Category c : cs) {
	            System.out.println(c.getName());
	        }
	}
}
