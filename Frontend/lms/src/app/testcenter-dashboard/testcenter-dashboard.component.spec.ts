import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestcenterDashboardComponent } from './testcenter-dashboard.component';

describe('TestcenterDashboardComponent', () => {
  let component: TestcenterDashboardComponent;
  let fixture: ComponentFixture<TestcenterDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TestcenterDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TestcenterDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
