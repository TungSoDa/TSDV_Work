import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteQuestionModalComponent } from './delete-modal.component';

describe('DeleteModalComponent', () => {
  let component: DeleteQuestionModalComponent;
  let fixture: ComponentFixture<DeleteQuestionModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteQuestionModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteQuestionModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
