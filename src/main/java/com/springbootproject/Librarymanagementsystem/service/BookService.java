package com.springbootproject.Librarymanagementsystem.service;


import com.springbootproject.Librarymanagementsystem.model.Book;
import com.springbootproject.Librarymanagementsystem.model.User;
import com.springbootproject.Librarymanagementsystem.repo.BookRepository;
import com.springbootproject.Librarymanagementsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private UserRepository userRepository;

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId,Book updatedBook){
        Book currentBook = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("User Not Found"));

        if(currentBook !=null){
            if(!updatedBook.getAuthor().isEmpty()){
                currentBook.setAuthor(updatedBook.getAuthor());
            }

            if(!updatedBook.getTitle().isEmpty()){
                currentBook.setTitle(updatedBook.getTitle());
            }

            if(!updatedBook.getBorrowedBy().getName().isEmpty()){
                String userName = updatedBook.getBorrowedBy().getName();
                Optional<User> user = userRepository.findByName(userName);;
                currentBook.setBorrowedBy(user.orElse(null));
            }

            currentBook.setBorrowed(updatedBook.isBorrowed());
        }

        if(currentBook != null){
            return bookRepository.save(currentBook);
        }
        return null;
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    public Book borrowBook(Long bookId,Long userId){
        Book book = findBookById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if(book != null && !book.isBorrowed() && user !=null){
            book.setBorrowedBy(user);
            book.setBorrowed(true);
            return saveBook(book);
        }

        return null;
    }

    public Book returnBook(Long bookId){
        Book book = findBookById(bookId);
        if(book !=null && book.isBorrowed()){
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            return saveBook(book);
        }

        return null;
    }

}
