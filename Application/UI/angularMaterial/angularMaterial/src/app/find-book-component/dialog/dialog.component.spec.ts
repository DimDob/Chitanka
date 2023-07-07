import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogOverviewExampleDialog } from './dialog.component';

describe('DialogComponent', () => {
  let component: DialogOverviewExampleDialog;
  let fixture: ComponentFixture<DialogOverviewExampleDialog>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogOverviewExampleDialog]
    });
    fixture = TestBed.createComponent(DialogOverviewExampleDialog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
