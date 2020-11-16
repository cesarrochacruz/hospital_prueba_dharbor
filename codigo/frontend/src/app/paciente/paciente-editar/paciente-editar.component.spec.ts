import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalEditarComponent } from './hospital-editar.component';

describe('HospitalEditarComponent', () => {
  let component: HospitalEditarComponent;
  let fixture: ComponentFixture<HospitalEditarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HospitalEditarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HospitalEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
