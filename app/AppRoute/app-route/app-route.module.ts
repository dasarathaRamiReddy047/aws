import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from 'src/app/components/home/home.component';
import { LoginComponent } from 'src/app/components/login/login.component';
import { EmployeeComponent } from 'src/app/components/employee/employee.component';
import { RegisterComponent } from 'src/app/components/register/register.component';
import { AdminComponent } from 'src/app/components/admin/admin.component';
import { AdminSalaryComponent } from 'src/app/components/admin-salary/admin-salary.component';
import { SalaryComponent } from 'src/app/components/salary/salary.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'employee', component: EmployeeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'adminSalary', component: AdminSalaryComponent },
  { path: 'salary', component: SalaryComponent },
];

@NgModule({
  declarations: [],
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRouteModule {}
