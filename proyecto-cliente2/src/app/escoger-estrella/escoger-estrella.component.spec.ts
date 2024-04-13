import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EscogerEstrellaComponent } from './escoger-estrella.component';

describe('EscogerEstrellaComponent', () => {
  let component: EscogerEstrellaComponent;
  let fixture: ComponentFixture<EscogerEstrellaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EscogerEstrellaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EscogerEstrellaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
