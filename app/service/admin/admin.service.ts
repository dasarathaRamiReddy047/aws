import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private http: HttpClient) {}

  // Employeee

  empUrl = 'http://localhost:4000/employee';

  getEmployeeDetails() {
    return this.http.get(this.empUrl);
  }

  insertEmployee(body: any) {
    return this.http.post(this.empUrl, body);
  }

  getEmployeeById(id: number) {
    return this.http.get(this.empUrl + '/' + id);
  }

  updateEmployeeById(id: number, body: any) {
    return this.http.put(this.empUrl + '/' + id, body);
  }

  deleteEmployeeById(id: number) {
    return this.http.delete(this.empUrl + '/' + id);
  }
}
