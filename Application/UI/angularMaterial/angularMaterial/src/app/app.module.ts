import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field'; 
import {MatDividerModule} from '@angular/material/divider';
import { BookOperationsModalComponent } from './add-book-component/request-book.component';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { DialogOverviewExampleDialog } from "./add-book-component/dialog/dialog.component";
import { DeleteBookComponent } from './delete-book-component/delete-book.component';
import { FindBookComponent } from './find-book-component/find-book.component';
import { AllBooksComponentModal } from './show-all-books-component/show-all-books.component';
@NgModule({
    declarations: [
        AppComponent,
        BookOperationsModalComponent,
        DeleteBookComponent,
        FindBookComponent,
    ],
    providers: [
        { provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: { hasBackdrop: false } }
    ],
    bootstrap: [AppComponent],
    imports: [
        NoopAnimationsModule,
        AppRoutingModule,
        FormsModule,
        MatDialogModule,
        MatFormFieldModule,
        MatDividerModule,
        MatTableModule,
        MatInputModule,
        DialogOverviewExampleDialog,
        AllBooksComponentModal
    ]
})
export class AppModule { }
