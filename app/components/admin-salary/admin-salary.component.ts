import { SalaryService } from './../../service/adminsalary/salary.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/service/admin/admin.service';

@Component({
  selector: 'app-admin-salary',
  templateUrl: './admin-salary.component.html',
  styleUrls: ['./admin-salary.component.css'],
})
export class AdminSalaryComponent implements OnInit {
  constructor(
    private salaryService: SalaryService,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.gettingSalaryDetails();
  }

  salaryDetailsList: any;

  gettingSalaryDetails() {
    this.salaryService.gettingSalaryDetails().subscribe((res) => {
      console.log(res);
      this.salaryDetailsList = res;
    });
  }

  // getBy EmployeeID

  salaryTable: boolean = false;

  salary: string = '';
  date: string = '';
  employeeId: number = 0;
  salaryId: number = 0;

  employeeDetails: any;

  salaryListData: any;

  update(salaryid: any) {
    this.salaryTable = true;
    this.isupdateSalary = true;
    console.log(salaryid);
    this.salaryService.gettingsalarydetailsById(salaryid).subscribe((res) => {
      this.salaryListData = res;
      this.employeeDetails = this.salaryListData.employee;
      this.salaryId = salaryid;
      this.employeeId = this.employeeDetails.id;

      this.salary = this.salaryListData.amount;
      this.date = this.salaryListData.salary_credited_date;
    });
  }

  //

  isupdateSalary: boolean = false;

  updateSalary() {
    let data = {
      amount: this.salary,
      salary_credited_date: this.date,
      employee: this.employeeDetails,
    };

    this.salaryService.updateSalary(this.salaryId, data).subscribe((res) => {
      console.log(res);
      alert('Salary Updated');
      this.isupdateSalary = false;
      this.gettingSalaryDetails();
      this.salaryTable = false;
    });

    console.log(data);
  }

  // addsalary
  isAddSalary: boolean = false;

  addButton() {
    this.isAddSalary = !this.isAddSalary;
    this.salaryTable = !this.salaryTable;
    this.isupdateSalary = false;

    this.salary = '';
    this.date = '';
    this.employeeId = 0;
    this.salaryId = 0;
  }

  addEmployeeDetails: any;

  addSalary() {
    console.log(this.employeeId);
    this.adminService.getEmployeeById(this.employeeId).subscribe(
      (res) => {
        this.addEmployeeDetails = res;

        let data = {
          amount: this.salary,
          salary_credited_date: this.date,
          employee: this.addEmployeeDetails,
        };
        console.log(data);

        this.salaryService.insertSalary(data).subscribe(
          (res) => {
            alert('Salary Added');
            this.isAddSalary = false;
            this.salaryTable = false;
            this.gettingSalaryDetails();

            this.salary = '';
            this.date = '';
            this.employeeId = 0;
            this.salaryId = 0;
          },
          (error) => {
            alert('Employee Not Added');
          }
        );
      },
      (error) => {
        alert('Employee Not Found');
      }
    );
  }

  delete(id: any) {
    this.salaryService.deleteById(id).subscribe((res) => {
      alert('Salary Deleted');
      this.gettingSalaryDetails();
    });
  }
}
