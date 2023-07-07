import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllBooksComponentModal } from './show-all-books.component';

describe('ShowAllBooksComponentModalComponent', () => {
  let component: AllBooksComponentModal;
  let fixture: ComponentFixture<AllBooksComponentModal>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllBooksComponentModal]
    });
    fixture = TestBed.createComponent(AllBooksComponentModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
