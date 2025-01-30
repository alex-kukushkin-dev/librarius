package com.demo.library.service.impl;

import com.demo.library.entity.Book;
import com.demo.library.entity.BorrowRecord;
import com.demo.library.entity.User;
import com.demo.library.exception.UserFriendlyException;
import com.demo.library.repository.BorrowRecordRepository;
import com.demo.library.service.BookService;
import com.demo.library.service.BorrowService;
import com.demo.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookService bookService;
    private final UserService userService;

    public BorrowRecord borrowBook(String username, Long bookId) {
        User user = userService.findByUsername(username)
            .orElseThrow(() -> new UserFriendlyException("User not found"));
        Book book = bookService.findById(bookId)
            .orElseThrow(() -> new UserFriendlyException("Book not found"));
        if (!book.isAvailable()) {
            throw new UserFriendlyException("The book is not available.");
        }
        book.setAvailable(false);
        bookService.updateBook(book);
        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDateTime.now());
        return borrowRecordRepository.save(record);
    }

    public BorrowRecord returnBook(Long borrowRecordId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId)
            .orElseThrow(() -> new UserFriendlyException("Borrow record not found"));
        record.setReturnDate(LocalDateTime.now());
        BorrowRecord updatedRecord = borrowRecordRepository.save(record);
        Book book = updatedRecord.getBook();
        book.setAvailable(true);
        bookService.updateBook(book);
        return updatedRecord;
    }

    public List<BorrowRecord> getBorrowedBooks(String username) {
        User user = userService.findByUsername(username)
            .orElseThrow(() -> new UserFriendlyException("User not found"));
        List<BorrowRecord> records = borrowRecordRepository.findByUserId(user.getId());
        return records.stream()
                  .filter(r -> r.getReturnDate() == null)
                  .collect(Collectors.toList());
    }
}
