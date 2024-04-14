import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EscogerPlanetaComponent } from './escoger-planeta.component';

describe('EscogerPlanetaComponent', () => {
  let component: EscogerPlanetaComponent;
  let fixture: ComponentFixture<EscogerPlanetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EscogerPlanetaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EscogerPlanetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
