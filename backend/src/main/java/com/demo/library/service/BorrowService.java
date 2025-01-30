package com.demo.library.service;

import com.demo.library.entity.BorrowRecord;

import java.util.List;

public interface BorrowService {
    BorrowRecord borrowBook(String username, Long bookId);
    BorrowRecord returnBook(Long borrowRecordId);
    List<BorrowRecord> getBorrowedBooks(String username);
}