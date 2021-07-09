import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContributorExamComponent } from './exam.component';

describe('ExamComponent', () => {
  let component: ContributorExamComponent;
  let fixture: ComponentFixture<ContributorExamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContributorExamComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContributorExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
