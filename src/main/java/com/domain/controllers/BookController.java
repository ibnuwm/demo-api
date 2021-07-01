package com.domain.controllers;

import java.util.ArrayList;
import java.util.List;

import com.domain.dto.ResponseData;
import com.domain.models.entities.Book;
import com.domain.services.BookService;
import com.domain.utils.CSVHelper;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> findAllBook(
            @RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        ResponseData<List<Book>> responseData = new ResponseData<>();

        try {
            List<Book> books = bookService.findAll(isDeleted);
            responseData.setStatus(true);
            responseData.setPayload(books);
            responseData.getMessages().add("get all books");
            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setStatus(false);
            responseData.getMessages().add("could not get books: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        ResponseData<List<Book>> response = new ResponseData<>();

        if (!CSVHelper.hasCSVFormat(file)) {
            response.setStatus(false);
            response.getMessages().add("Please upload the CSV File");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {

            List<Book> books = bookService.save(file);
            response.setStatus(true);
            response.setPayload(books);
            response.getMessages().add("Uploaded file Succesfully: " + file.getOriginalFilename());
            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            response.setStatus(false);
            response.getMessages().add("could not upload file: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }

    }

    @PostMapping
    public Book createOne(@RequestBody Book book) {
        return bookService.create(book);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        bookService.remove(id);
    }

}
