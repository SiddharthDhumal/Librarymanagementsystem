package com.springbootproject.Librarymanagementsystem.repo;

import com.springbootproject.Librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book,Long> {
}
