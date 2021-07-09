import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContributorHomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: ContributorHomeComponent;
  let fixture: ComponentFixture<ContributorHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContributorHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContributorHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
