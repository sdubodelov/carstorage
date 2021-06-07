import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarNotFoundComponent } from './car-not-found.component';

describe('CarNotFoundComponent', () => {
  let component: CarNotFoundComponent;
  let fixture: ComponentFixture<CarNotFoundComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarNotFoundComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarNotFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
