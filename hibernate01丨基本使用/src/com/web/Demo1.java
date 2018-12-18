package com.web;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.domain.User;

public class Demo1 {
	@Test
	public void fun1() {
		// 1.创建hibernate配置类
		/**
		 * configure() 空参，默认情况下加载src目录下 hibernate.cfg.xml
		 * configure(String resource)  可以直接填写配置文件的字符串路径
		 * configure(File configFile)  可以根据file文件对象加载配置
		 */
		Configuration conf = new Configuration().configure();

		/**
		 * SessionFactory 由 conf.buildSessionFactory() 创建
		 * SessionFactory 在整个项目中只需创建一个
		 * SessionFactory 是专门用来构建session
		 */
		// 2.创建一个SessionFactory
		SessionFactory SessionFactory = conf.buildSessionFactory();

		/** 
		 * session是hibernate中的核心 API，hibernate中增删改查是由session完成
		 * session.save()		增加
		 * session.get()		查询
		 * session.delete()		删除
		 * session.update()		修改
		 */
		// 3.创建session对象
		Session session = SessionFactory.openSession();

		/**
		 * Transaction 是hibernate中事务对象，专门用来操作对事务相关的
		 * 
		 * 开启事务：session.beginTransaction()
		 * 提交事务：Transaction.commit()
		 * 回滚事务：Transaction.rollback()
		 */
		// 开启事务
		Transaction tx = session.beginTransaction();

		User user = new User();
		user.setUsername("王五");
		user.setPassword("123");
		// 添加
		session.save(user);

		// 提交事务
		tx.commit();

		// 关闭资源
		session.close();
		SessionFactory.close();
	}
}
