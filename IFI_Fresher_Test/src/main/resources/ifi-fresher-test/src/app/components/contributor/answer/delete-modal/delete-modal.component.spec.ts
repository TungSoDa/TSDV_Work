import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteAnswerModalComponent } from './delete-modal.component';

describe('DeleteModalComponent', () => {
  let component: DeleteAnswerModalComponent;
  let fixture: ComponentFixture<DeleteAnswerModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteAnswerModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteAnswerModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
