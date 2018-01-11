package com.spring.henallux.dataAccess.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.Model.Book;
import com.spring.henallux.dataAccess.entity.BookEntity;
import com.spring.henallux.dataAccess.util.ProviderConverter;

@Service
@Transactional
public class BookDAO {

	@Autowired
	private ProviderConverter converter = new ProviderConverter();
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public ArrayList<Book> getBooksByCategory (Integer category_id)
	{
		ArrayList<Book> booksModel = new ArrayList<Book>();
		Collection<BookEntity> booksEntity;
		
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("findBooksByCategory").setInteger("category", category_id);
		booksEntity = query.list();
		
		for(BookEntity bookEntity : booksEntity)
		{
			Book book = new Book();
			book = converter.bookEntityToBookModel(bookEntity);
			booksModel.add(book);
		}
		
		session.getTransaction().commit();
		return booksModel;
	}
}
