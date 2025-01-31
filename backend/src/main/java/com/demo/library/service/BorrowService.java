package com.demo.library.service;

import com.demo.library.entity.BorrowRecord;

import java.util.List;

public interface BorrowService {
    Void borrowBook(Long userId, Long bookId);
    Void returnBook(Long userId, Long bookId);
    List<BorrowRecord> getBorrowedBooks(String username);
}