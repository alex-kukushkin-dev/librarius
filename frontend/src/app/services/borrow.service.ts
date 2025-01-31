import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Borrow } from '../models/borrow.model';

@Injectable({
    providedIn: 'root'
})
export class BorrowService {
    private baseUrl = 'http://localhost:8080/api/borrow';
    constructor(private http: HttpClient) {}

    borrowBook(userId: number, bookId: number): Observable<void> {
        const params = new HttpParams()
            .set('userId', userId)
            .set('bookId', bookId);
        return this.http.post<void>(`${this.baseUrl}`, null, { params });
    }

    returnBook(userId: number, bookId: number): Observable<void> {
        const params = new HttpParams()
            .set('userId', userId)
            .set('bookId', bookId);
        return this.http.post<void>(`${this.baseUrl}/return`, null, { params });
    }

  getUserBorrowedBooks(username: string): Observable<Borrow[]> {
        const params = new HttpParams()
            .set('username', username)
        return this.http.get<Borrow[]>(`${this.baseUrl}/borrowed-books`, { params });
    }
}
