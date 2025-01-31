import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    imports: [
        NgIf,
        RouterOutlet,
        RouterLink
    ],
    standalone: true
})
export class AppComponent {
    constructor(private authService: AuthService, private router: Router) {}

    isLoggedIn(): boolean {
        return !!this.authService.getLoggedUser();
    }

    logout() {
        this.authService.logout();
        this.router.navigate(['/login']).then(() => {});
    }
}
