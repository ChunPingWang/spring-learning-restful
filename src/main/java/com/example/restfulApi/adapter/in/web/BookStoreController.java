package com.example.restfulApi.adapter.in.web;

import com.example.restfulApi.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {

   @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        // 暫時這樣寫，後續再重構從資料庫查詢
         if(bookId==1111L)
             return ResponseEntity.ok(new Book("Java Programming", 100.0));
         else
             return ResponseEntity.notFound().build();
    }
}