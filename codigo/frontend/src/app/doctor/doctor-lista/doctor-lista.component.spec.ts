import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalListaComponent } from './hospital-lista.component';

describe('HospitalListaComponent', () => {
  let component: HospitalListaComponent;
  let fixture: ComponentFixture<HospitalListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HospitalListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HospitalListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
