import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class EmployeeSalaryService {
  constructor(private http: HttpClient) {}

  url = 'http://localhost:4000/salary/employee/';

  gettingSalarySlipByEmployeeId(id: any) {
    return this.http.get(this.url + id);
  }

  salaryUrl = 'http://localhost:4000/salary/';

  gettingSalaryById(id: any) {
    return this.http.get(this.salaryUrl + id);
  }
}
