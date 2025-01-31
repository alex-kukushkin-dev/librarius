import { Component } from '@angular/core';
import { BorrowService } from '../../../services/borrow.service';
import { DateUtil } from '../../../shared/date.util';
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
    username: string = "";
    borrowedBooks: Borrow[] = [];

    constructor(private borrowService: BorrowService, private dateUtil: DateUtil) {}

    loadBorrowedBooks(): void {
        if (!this.username) {
            alert('The user is not provided.');
            return;
        }
        this.borrowService.getUserBorrowedBooks(this.username)
            .subscribe(
            {
                next: result => {
                    for (const borrow of result) {
                        const borrowDate = borrow.borrowDate;
                        borrow.borrowDate = this.dateUtil.formatDate(borrowDate);
                    }
                    this.borrowedBooks = result;
                },
                error: error => {
                    alert('Error fetching borrowed books: ' + error.message);
                }
            });
    }
}
