import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InformacionPlanetaComponent } from './informacion-planeta.component';

describe('InformacionPlanetaComponent', () => {
  let component: InformacionPlanetaComponent;
  let fixture: ComponentFixture<InformacionPlanetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InformacionPlanetaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InformacionPlanetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
