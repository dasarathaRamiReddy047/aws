import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRouteModule } from './AppRoute/app-route/app-route.module';
import { SalaryService } from './service/adminsalary/salary.service';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { AdminComponent } from './components/admin/admin.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { EmployeeComponent } from './components/employee/employee.component';

import { AdminService } from './service/admin/admin.service';
import { AdminSalaryComponent } from './components/admin-salary/admin-salary.component';
import { LoginService } from './service/loginService/login.service';
import { SalaryComponent } from './components/salary/salary.component';
import { EmployeeSalaryService } from './service/salary/employee-salary.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    AboutComponent,
    AdminComponent,
    LoginComponent,
    RegisterComponent,
    EmployeeComponent,
    AdminSalaryComponent,
    SalaryComponent,
  ],
  imports: [
    BrowserModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    AppRouteModule,
  ],
  providers: [AdminService, SalaryService, LoginService, EmployeeSalaryService],
  bootstrap: [AppComponent],
})
export class AppModule {}
