import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAnswerModalComponent } from './edit-modal.component';

describe('EditModalComponent', () => {
  let component: EditAnswerModalComponent;
  let fixture: ComponentFixture<EditAnswerModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditAnswerModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAnswerModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
