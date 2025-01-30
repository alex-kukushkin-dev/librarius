import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

@Component({
    selector: 'app-user-form',
    templateUrl: './user-form.component.html',
    styleUrls: ['./user-form.component.css'],
    imports: [CommonModule, FormsModule],
    standalone: true
})
export class UserFormComponent implements OnInit {
    userId?: number;
    user: User = {
        username: ''
    };

    isEditMode = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private userService: UserService
    ) {}

    ngOnInit(): void {
        this.userId = Number(this.route.snapshot.paramMap.get('id'));
        this.isEditMode = !!this.userId;

        if (this.isEditMode) {
            this.userService.getUserById(this.userId!)
                .subscribe(
                    {
                        next:  data => { this.user = data; },
                        error: () => {
                            alert('User not found: ' + this.userId);
                            this.router.navigate(['/users']).then(() => {});
                        }
                });
        }
    }

    saveUser(): void {
        if (this.isEditMode && this.userId != null) {
            this.userService.updateUser(this.userId, this.user)
                .subscribe(() => {
                    alert('User updated successfully');
                    this.router.navigate(['/users']).then(() => {});
                });
        } else {
            this.userService.createUser(this.user)
                .subscribe(() => {
                    alert('User created successfully');
                    this.router.navigate(['/users']).then(() => {});
                });
        }
    }
}
