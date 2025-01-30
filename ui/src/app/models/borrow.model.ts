import { User } from './user.model';
import { Book } from './book.model';

export interface Borrow {
    id?: number;
    user: User;
    book: Book;
    borrowDate: string;
    returnDate: string;
}
