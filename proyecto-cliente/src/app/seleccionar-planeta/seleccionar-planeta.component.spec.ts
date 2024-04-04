import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeleccionarPlanetaComponent } from './seleccionar-planeta.component';

describe('SeleccionarPlanetaComponent', () => {
  let component: SeleccionarPlanetaComponent;
  let fixture: ComponentFixture<SeleccionarPlanetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeleccionarPlanetaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SeleccionarPlanetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
