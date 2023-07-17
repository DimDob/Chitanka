import { NgIf } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import swal from 'sweetalert2';
import { HttpClient, HttpClientModule } from '@angular/common/http';

export interface DialogData {
  bookTitle: string;
}

export interface Book {
  id: string
  title: string;
  author: any;
  link: string;
}

@Component({
  standalone: true,
  imports: [MatInputModule, FormsModule, MatFormFieldModule, MatDialogModule, NgIf, HttpClientModule],
  selector: 'dialog-overview-example-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css'],
})
export class DialogOverviewExampleDialog {
  afterOkClickShown: boolean = false;

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private http: HttpClient
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  async onClickOKButton(): Promise<void> {
    if (this.data.bookTitle != undefined) {
      this.data.bookTitle = this.data.bookTitle.replace(/"/g, '')
      const bookFound = await this.searchForTheBookInTheDatabase(this.data.bookTitle);
      if (bookFound) {
        this.publishBookFoundMessage(bookFound)
        this.sweetAlert("Great! You have requested the online book: " + this.data.bookTitle + ", written by " + bookFound.author.name + ". \n\nIt will be sent to your email address right away!");
        this.afterOkClickShown = true;
      }
    } else {
      this.sweetAlert("Please enter a valid book title!");
    }
  }

  sweetAlert(alertMessage: string) {
    swal.fire(alertMessage);
  }

  onCloseSecondDialog(): void {
    this.afterOkClickShown = false;
    this.dialogRef.close();
  }

  searchForTheBookInTheDatabase(bookTitle: string): Promise<Book> {

    return new Promise<Book>((resolve) => {
      this.http.get("http://localhost:8080/books").subscribe((response: any) => {
        const pages = Array(response['content']);
        for (const books of pages) {
          for (const book of books) {
            if (book.title.replace(/"/g, '').toLowerCase() == bookTitle.replace(/"/g, '').toLowerCase()) {
              resolve(book);
              return book;
            }
          }
        }

        this.sweetAlert("The book " + this.data.bookTitle + " has not been found in the library.");

      }, (error) => {
        console.error("Error occurred while fetching books:", error);
      });
    });
  }

  publishBookFoundMessage(bookFound: Book) {
    
    return this.http.put(`http://localhost:8080/books/${bookFound.id}/loan`, true).subscribe((response: any) => {
      return response
  
    })
  }
}


