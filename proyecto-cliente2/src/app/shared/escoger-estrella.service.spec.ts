import { TestBed } from '@angular/core/testing';

import { EscogerEstrellaService } from './escoger-estrella.service';

describe('EscogerEstrellaService', () => {
  let service: EscogerEstrellaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EscogerEstrellaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
