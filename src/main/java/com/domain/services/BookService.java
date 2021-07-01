package com.domain.services;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import com.domain.models.entities.Book;
import com.domain.models.repos.BookRepo;
import com.domain.utils.CSVHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("bookService")
@Transactional
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> save(MultipartFile file) {
        try {
            List<Book> books = CSVHelper.csvToBook(file.getInputStream());
            return bookRepo.saveAll(books);
        } catch (IOException ex) {
            throw new RuntimeException("fail to store csv data: " + ex.getMessage());
        }
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }
}
