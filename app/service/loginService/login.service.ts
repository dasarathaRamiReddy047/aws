import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  url = 'http://localhost:4000/employee';

  isLogin: boolean = false;

  loginDetails: any;

  loginEmployeeId: any;

  gettingEmployeeDetailsByEmployeeId(id: any) {
    return this.http.get(this.url + '/' + id);
  }
}
