import { TestBed } from '@angular/core/testing';

import { ApiRsaService } from './api-rsa.service';

describe('ApiRsaService', () => {
  let service: ApiRsaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiRsaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
