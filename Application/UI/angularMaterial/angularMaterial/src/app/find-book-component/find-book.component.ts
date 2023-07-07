import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogOverviewExampleDialog } from './dialog/dialog.component';

@Component({
  selector: 'app-find-book',
  templateUrl: './find-book.component.html',
  styleUrls: ['./find-book.component.css']
})
export class FindBookComponent {


  bookTitle?: string;
  dialogRef: any; 
  afterOkClickShown: any;

  constructor(public dialog: MatDialog) { }

  openDialog(): void {
    if (this.dialogRef && this.dialogRef.componentInstance) {
      this.dialogRef.componentInstance.onNoClick();
      this.dialogRef = null;
    } else {
      this.dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
        data: { bookTitle: this.bookTitle },
      });

      this.dialogRef.afterClosed().subscribe(() => {
        console.log('The dialog was closed');
        this.dialogRef = null;
      });
    }
  }

  onClickOKButton(): void {
    this.afterOkClickShown = true;
    
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      data: { bookTitle: '' }, 
      
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      this.afterOkClickShown = true;

    });    
}
  onCloseSecondDialog(): void {
    this.afterOkClickShown = false;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  //TODO after migration in DB is created for chosen books, delete modal should remove a book from the user's repository as well.


}
