import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalizarPartidaComponent } from './finalizar-partida.component';

describe('FinalizarPartidaComponent', () => {
  let component: FinalizarPartidaComponent;
  let fixture: ComponentFixture<FinalizarPartidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FinalizarPartidaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FinalizarPartidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
