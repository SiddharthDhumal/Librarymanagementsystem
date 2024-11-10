package com.springbootproject.Librarymanagementsystem.controller;


import com.springbootproject.Librarymanagementsystem.model.Book;
import com.springbootproject.Librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book findBookById(@PathVariable Long bookId){
        return bookService.findBookById(bookId);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId,@RequestBody Book book){
        Book correctBook = bookService.findBookById(bookId);
        if(correctBook != null){
            return bookService.updateBook(bookId,book);
        }
        return null;
    }

    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable Long bookId){
         bookService.deleteBookById(bookId);
    }

    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @PathVariable Long userId){
        Book borrowedBook =  bookService.borrowBook(bookId,userId);

        if(borrowedBook != null){
             return ResponseEntity.ok(borrowedBook);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId){
        Book returnedBook =  bookService.returnBook(bookId);

        if(returnedBook != null){
            return ResponseEntity.ok(returnedBook);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
