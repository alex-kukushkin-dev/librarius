import { Component, OnInit } from '@angular/core';
import { Book } from '../../../models/book.model';
import { BookService } from '../../../services/book.service';
import { BorrowService } from '../../../services/borrow.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from "@angular/router";
import { FormsModule } from "@angular/forms";

@Component({
    selector: 'app-book-list',
    templateUrl: './book-list.component.html',
    styleUrls: ['./book-list.component.css'],
    imports: [CommonModule, RouterLink, FormsModule],
    standalone: true
})
export class  BookListComponent implements OnInit {
    books: Book[] = [];
    searchText = '';
    page = 0;
    size = 5;
    totalPages = 0;
    selectedUser = 'admin';

    constructor(
        private bookService: BookService,
        private borrowService: BorrowService
    ) {}

    ngOnInit(): void {
      this.loadBooks();
    }

    loadBooks(): void {
        this.bookService.getBooks(this.searchText, this.page, this.size)
            .subscribe(response => {
              this.books = response.content;
              this.totalPages = response.totalPages;
            });
    }

    onSearch(): void {
        this.page = 0;
        this.loadBooks();
    }

    prevPage(): void {
        if (this.page > 0) {
            this.page--;
            this.loadBooks();
        }
    }

    nextPage(): void {
        if (this.page < this.totalPages - 1) {
            this.page++;
            this.loadBooks();
        }
    }

    borrowBook(bookId: number): void {
        this.borrowService.borrowBook(this.selectedUser, bookId)
            .subscribe({
                next: () => {
                    this.loadBooks();
                }, error: error => {
                    alert('Failed to borrow: ' + error.message);
                }
            });
    }

    returnBook(bookId: number): void {
        this.borrowService.returnBook(bookId)
            .subscribe({
                next: () => {
                    this.loadBooks();
                },
                error: error => {
                    alert('Failed to return: ' + error.message);
                }
            });
    }
}
