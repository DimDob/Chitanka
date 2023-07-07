import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { NgIf } from '@angular/common';

export interface Book {
  position: number;
  title: string;
  author: string;
  link: string;
}

@Component({
  standalone: true,
  selector: 'table-basic-example',
  styleUrls: ['show-all-books.component.css'],
  templateUrl: 'show-all-books.component.html',
  imports: [MatTableModule, NgIf ],

})
export class AllBooksComponentModal {
  afterShowButtonIsClicked: any;
  books: Book[] = [];
  dataSource: Book[] = [];
  displayedColumns: string[] = ['position', 'title', 'author', 'link'];

  constructor(private http: HttpClient) {}

  renderGrid() {
    this.afterShowButtonIsClicked = true;
    this.collectBooksFromDatabase();
  }

  collectBooksFromDatabase() {
    this.http.get<Book[]>('http://localhost:8080/books').subscribe((response: any) => {
      let position = 0;
      const books: Book[] = [];
      for (const currentBook of response['content']) {
        position += 1;
        const book: Book = {
          position: position,
          title: currentBook.title.replace(/"/g, ''),
          author: currentBook.author.name,
          link: currentBook.link.replace(/"/g, ''),
        };
        
        books.push(book);
      }
      
      this.dataSource = books;
    });
  }
  
  
}
