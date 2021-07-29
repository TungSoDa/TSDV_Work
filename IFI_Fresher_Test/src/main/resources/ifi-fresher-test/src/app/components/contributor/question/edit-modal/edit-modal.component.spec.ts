import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditQuestionModalComponent } from './edit-modal.component';

describe('EditModalComponent', () => {
  let component: EditQuestionModalComponent;
  let fixture: ComponentFixture<EditQuestionModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditQuestionModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditQuestionModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
