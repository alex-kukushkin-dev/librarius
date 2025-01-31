import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../../../models/book.model';
import { BookService } from '../../../services/book.service';
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

@Component({
    selector: 'app-book-form',
    templateUrl: './book-form.component.html',
    styleUrls: ['./book-form.component.css'],
    imports: [CommonModule, FormsModule],
    standalone: true
})
export class BookFormComponent implements OnInit {
    bookId?: number;
    book: Book = {
        title: '',
        author: '',
        available: true
    };

    isEditMode = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private bookService: BookService
    ) {
    }

    ngOnInit(): void {
        this.bookId = Number(this.route.snapshot.paramMap.get('id'));
        this.isEditMode = !!this.bookId;

        if (this.isEditMode) {
            this.bookService.getBookById(this.bookId!)
                .subscribe({
                    next: (data) => {
                        this.book = data;
                    },
                    error: (error) => {
                        alert('Could not find book with ID ' + this.bookId);
                        this.router.navigate(['/books']).then(r => {});
                  }
              });
        }
    }

    saveBook(): void {
        if (this.isEditMode && this.bookId != null) {
            this.bookService.updateBook(this.bookId, this.book)
                .subscribe(() => {
                    alert('Book updated successfully');
                    this.router.navigate(['/books']).then(() => {});
                });
        } else {
            this.bookService.createBook(this.book)
                .subscribe(() => {
                    alert('Book created successfully');
                    this.router.navigate(['/books']).then(() => {});
                });
        }
    }
}
