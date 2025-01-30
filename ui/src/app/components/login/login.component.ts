import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user.model';
import { FormsModule } from "@angular/forms";
import { NgIf } from "@angular/common";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    imports: [
        FormsModule,
        NgIf
    ],
    standalone: true
})
export class LoginComponent {
    username: string = '';
    errorMessage: string = '';

    constructor(private authService: AuthService, private router: Router) {}

    login() {
        if (!this.username.trim()) {
            this.errorMessage = 'Please enter a username.';
            return;
        }
        this.authService.login(this.username)
            .subscribe({
                next: (user: User) => {
                    this.authService.storeLoggedUser(user);
                    this.router.navigate(['/books']).then(() => {});
                },
                error: err => {
                    this.errorMessage = 'Login failed: ' + err.message;
                }
        });
    }
}
