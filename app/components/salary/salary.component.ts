import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/loginService/login.service';
import { EmployeeSalaryService } from 'src/app/service/salary/employee-salary.service';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

export interface Alert {
  type: string;
  message: string;
}

const ALERTS: Alert[] = [
  {
    type: 'info',
    message: 'Pay Slip is Downloading......',
  },
  { type: 'success', message: 'This is a success alert' },
];

@Component({
  selector: 'app-salary',
  templateUrl: './salary.component.html',
  styleUrls: ['./salary.component.css'],
})
export class SalaryComponent implements OnInit {
  constructor(
    private employeeSalaryService: EmployeeSalaryService,
    private loginSerivce: LoginService
  ) {}

  employeeID: any;

  ngOnInit(): void {
    this.gettingEmployeeId();
    this.gettingSalary();
  }

  gettingEmployeeId() {
    this.employeeID = this.loginSerivce.loginEmployeeId;
    console.log(this.employeeID);
  }

  SalarySlipsList: any;

  gettingSalary() {
    console.log(this.employeeID);
    this.employeeSalaryService
      .gettingSalarySlipByEmployeeId(this.employeeID)
      .subscribe((res) => {
        this.SalarySlipsList = res;
      });
  }

  salarySlip: boolean = false;

  currentDate: any;
  employeeName: any;
  salrayEmployeeId: string = '';
  employeeCategory: string = '';
  paymentDate: string = '';
  basicSalary: number = 0;
  taxAmount: number = 0.1;

  infoAlerts: any;

  EmployeesalarySlip: any;

  viewSalarySlip(id: any) {
    this.salarySlip = !this.salarySlip;
    console.log(id);
    this.employeeSalaryService.gettingSalaryById(id).subscribe((res) => {
      this.EmployeesalarySlip = res;
      this.basicSalary = this.EmployeesalarySlip.amount;
      this.employeeName = this.EmployeesalarySlip.employee.name;
      this.salrayEmployeeId = this.EmployeesalarySlip.employee.id;
      this.employeeCategory = this.EmployeesalarySlip.employee.category;
      this.paymentDate = this.EmployeesalarySlip.salary_credited_date;

      let date = new Date();
      this.currentDate = date.toLocaleDateString();
    });
  }

  // download
  downloadPaySlip() {
    let DATA: any = document.getElementById('salarySlip');
    html2canvas(DATA).then((canvas) => {
      let fileWidth = 208;
      let fileHeight = (canvas.height * fileWidth) / canvas.width;
      const FILEURI = canvas.toDataURL('image/png');
      let PDF = new jsPDF('p', 'mm', 'a4');
      let position = 0;
      PDF.addImage(FILEURI, 'PNG', 0, position, fileWidth, fileHeight);
      PDF.save('Salary Pay Slip.pdf');
      this.infoAlerts = [];
      this.reset();
    });
  }

  reset() {}

  calculateTotal() {
    let total = this.basicSalary * this.taxAmount;
    return this.basicSalary - total;
  }

  backButton() {
    this.salarySlip = false;
  }

  close(alert: Alert) {}
}
