import { TestBed } from '@angular/core/testing';

import { ApiFileService } from './api-file.service';

describe('ApiFileService', () => {
  let service: ApiFileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiFileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
