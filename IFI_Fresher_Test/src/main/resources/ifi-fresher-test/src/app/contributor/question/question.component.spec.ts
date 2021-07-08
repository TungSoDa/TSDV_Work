import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContributorQuestionComponent } from './question.component';

describe('QuestionComponent', () => {
  let component: ContributorQuestionComponent;
  let fixture: ComponentFixture<ContributorQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContributorQuestionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContributorQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
