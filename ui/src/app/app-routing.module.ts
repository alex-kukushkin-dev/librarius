import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from './components/book/book-list/book-list.component';
import { BookFormComponent } from './components/book/book-form/book-form.component';
import { UserListComponent } from './components/user/user-list/user-list.component';
import { UserFormComponent } from './components/user/user-form/user-form.component';
import { BorrowListComponent } from './components/borrow/borrow-list/borrow-list.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'books', component: BookListComponent },
    { path: 'add-book', component: BookFormComponent },
    { path: 'edit-book/:id', component: BookFormComponent },
    { path: 'users', component: UserListComponent },
    { path: 'add-user', component: UserFormComponent },
    { path: 'edit-user/:id', component: UserFormComponent },
    { path: 'borrow-list', component: BorrowListComponent },
    { path: '**', redirectTo: '' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
