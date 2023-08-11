import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin/admin.service';
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
})
export class EmployeeComponent implements OnInit {
  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.employeeDetails();
  }

  // forms

  employee: any = {
    name: '',
    category: '',
    gender: '',
    mailId: '',
    role: '',
  };

  name: string = '';
  category: string = '';
  gender: string = '';
  mailId: string = '';
  role: string = '';

  //employee Details

  isEmployeeAdd: boolean = false;

  add() {
    this.isEmployeeAdd = !this.isEmployeeAdd;
  }

  employeeList: any;

  employeeDetails() {
    this.adminService.getEmployeeDetails().subscribe((res) => {
      this.employeeList = res;
    });
  }

  // adding employee
  addEmployee() {
    this.adminService.insertEmployee(this.employee).subscribe((res) => {
      alert('Employee Added');
      this.employee = {
        name: '',
        category: '',
        gender: '',
        mailId: '',
        role: '',
      };
      this.employeeDetails();
    });
    console.log('Form submitted:', this.employee);
  }

  // update Employee
  isupdateEmployee: boolean = false;
  employeeId: number = 0;

  editDetails: any;
  editEmplyee(id: number) {
    this.isupdateEmployee = true;
    this.adminService.getEmployeeById(id).subscribe((res) => {
      this.editDetails = res;
      this.employeeId = this.editDetails.id;
      this.employee = {
        name: this.editDetails.name,
        category: this.editDetails.category,
        gender: this.editDetails.gender,
        mailId: this.editDetails.mailId,
        role: this.editDetails.role,
      };
    });
  }
  updateEmployee() {
    return this.adminService
      .updateEmployeeById(this.employeeId, this.employee)
      .subscribe((res) => {
        console.log('Employee Updated');
        this.employeeDetails();
        this.employee = {
          name: '',
          category: '',
          gender: '',
          mailId: '',
          role: '',
        };
        this.isupdateEmployee = false;
      });
  }

  deleteEmployee(id: number) {
    return this.adminService.deleteEmployeeById(id).subscribe((res) => {
      alert('Employee Deleted');
      this.employeeDetails();
    });
  }
}
