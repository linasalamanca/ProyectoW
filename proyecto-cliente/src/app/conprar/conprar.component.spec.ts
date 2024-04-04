import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConprarComponent } from './conprar.component';

describe('ConprarComponent', () => {
  let component: ConprarComponent;
  let fixture: ComponentFixture<ConprarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConprarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConprarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
