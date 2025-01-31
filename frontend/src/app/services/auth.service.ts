import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private baseUrl = '/api';
    constructor(private http: HttpClient) {}

    login(username: string): Observable<User> {
        return this.http.post<User>(`${this.baseUrl}/auth/login`, { responseType: 'text', username });
    }

    logout(): void {
        localStorage.removeItem('loggedUser');
    }

    storeLoggedUser(user: User) {
        localStorage.setItem('loggedUser', JSON.stringify(user));
    }

    getLoggedUser(): User | null {
        const data = localStorage.getItem('loggedUser');
        return data ? JSON.parse(data) : null;
    }
}
