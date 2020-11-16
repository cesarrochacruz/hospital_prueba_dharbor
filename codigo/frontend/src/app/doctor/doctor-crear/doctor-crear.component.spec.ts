import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalCrearComponent } from './hospital-crear.component';

describe('HospitalCrearComponent', () => {
  let component: HospitalCrearComponent;
  let fixture: ComponentFixture<HospitalCrearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HospitalCrearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HospitalCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
