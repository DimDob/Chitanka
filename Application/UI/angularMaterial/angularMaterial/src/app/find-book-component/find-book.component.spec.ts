import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindBookComponent } from './find-book.component';

describe('FindBookComponent', () => {
  let component: FindBookComponent;
  let fixture: ComponentFixture<FindBookComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FindBookComponent]
    });
    fixture = TestBed.createComponent(FindBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
