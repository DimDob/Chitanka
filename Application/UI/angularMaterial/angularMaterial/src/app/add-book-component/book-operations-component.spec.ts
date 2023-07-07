import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BookOperationsModalComponent } from './request-book.component';


describe('AddBookModal', () => {
  let component: BookOperationsModalComponent;
  let fixture: ComponentFixture<BookOperationsModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookOperationsModalComponent]
    });
    fixture = TestBed.createComponent(BookOperationsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
