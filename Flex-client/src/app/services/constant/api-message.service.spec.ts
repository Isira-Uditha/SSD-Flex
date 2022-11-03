import { TestBed } from '@angular/core/testing';

import { ApiMessageService } from './api-message.service';

describe('ApiMessageService', () => {
  let service: ApiMessageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiMessageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
