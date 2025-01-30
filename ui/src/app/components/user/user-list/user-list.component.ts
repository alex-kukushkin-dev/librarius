import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.css'],
    imports: [CommonModule, RouterLink],
    standalone: true
})
export class UserListComponent implements OnInit {
    users: User[] = [];

    constructor(private userService: UserService) {}

    ngOnInit(): void {
        this.loadUsers();
    }

    loadUsers(): void {
        this.userService.getAllUsers().subscribe(data => {
            this.users = data;
        });
    }

    deleteUser(id: number | undefined): void {
        if (!id) return;
        if (confirm('Are you sure you want to delete this user?')) {
            this.userService.deleteUser(id)
                .subscribe({
                    next: () => {
                        alert('User deleted');
                        this.loadUsers();
                    },
                    error: error => {
                        alert('Error deleting user: ' + error.message);
                    }
                });
        }
    }
}
