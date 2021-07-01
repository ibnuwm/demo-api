package com.domain.services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.domain.models.entities.Book;
import com.domain.models.repos.BookRepo;
import com.domain.utils.CSVHelper;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("bookService")
@Transactional
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private EntityManager entityManager;

    public List<Book> save(MultipartFile file) {
        try {
            List<Book> books = CSVHelper.csvToBook(file.getInputStream());
            return bookRepo.saveAll(books);
        } catch (IOException ex) {
            throw new RuntimeException("fail to store csv data: " + ex.getMessage());
        }
    }

    public List<Book> findAll(boolean isDeleted) {
        // return bookRepo.findAll();
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedBookFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Book> books = bookRepo.findAll();
        session.disableFilter("deletedBookFilter");
        return books;
    }

    public Book create(Book book) {
        return bookRepo.save(book);
    }

    public void remove(Long id) {
        bookRepo.deleteById(id);
    }
}
