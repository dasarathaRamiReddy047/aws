import {
  Component,
  TemplateRef,
  ViewEncapsulation,
  OnInit,
} from '@angular/core';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/loginService/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  constructor(
    private offcanvasService: NgbOffcanvas,
    private router: Router,
    private loginSerivce: LoginService
  ) {}

  //apps
  openCustomBackdropClass(content: TemplateRef<any>) {
    this.offcanvasService.open(content, { backdropClass: 'bg-info' });
  }

  loginPersonName: string = '';
  isLogin: boolean = false;

  Profilelogo() {
    this.loginPersonName = this.loginSerivce.loginDetails.name;
    this.isLogin = this.loginSerivce.isLogin;
  }
}
