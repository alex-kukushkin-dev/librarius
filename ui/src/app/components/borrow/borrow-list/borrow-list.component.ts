import { Component } from '@angular/core';
import { BorrowService } from '../../../services/borrow.service';
import { Borrow } from '../../../models/borrow.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-borrow-list',
    templateUrl: './borrow-list.component.html',
    styleUrls: ['./borrow-list.component.css'],
    imports: [CommonModule, FormsModule],
    standalone: true
})
export class BorrowListComponent {
    username: string = "admin";
    borrowedBooks: Borrow[] = [];

    constructor(private borrowService: BorrowService) {}

    loadBorrowedBooks(): void {
        this.borrowService.getUserBorrowedBooks(this.username)
            .subscribe(
            {
                next: result => {
                    this.borrowedBooks = result;
                },
                error: error => {
                    alert('Error fetching borrowed books: ' + error.message);
                }
            });
    }
}
