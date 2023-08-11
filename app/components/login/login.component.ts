import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { popper } from '@popperjs/core';
import { LoginService } from 'src/app/service/loginService/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(private loginService: LoginService, private router: Router) {}

  username: any;
  password: string = '';

  loginDetails: any = '';
  onSubmit() {
    return this.loginService
      .gettingEmployeeDetailsByEmployeeId(this.username)
      .subscribe(
        (res) => {
          this.loginDetails = res;
          this.loginService.loginDetails = res;
          this.loginService.loginEmployeeId = this.loginDetails.id;
          this.loginService.isLogin = true;

          if (
            this.loginDetails.id === parseInt(this.username) &&
            this.loginDetails.mailId === this.password
          ) {
            alert('login SuccessFul');
            this.router.navigate(['/']);
          }
        },
        (error) => {
          alert('Employee Id Incorrect');
        }
      );
  }
}
