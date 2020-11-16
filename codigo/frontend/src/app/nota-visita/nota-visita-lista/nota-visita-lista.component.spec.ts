import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotaVisitaListaComponent } from './nota-visita-lista.component';

describe('NotaVisitaListaComponent', () => {
  let component: NotaVisitaListaComponent;
  let fixture: ComponentFixture<NotaVisitaListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotaVisitaListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotaVisitaListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
