import {Component, OnInit} from '@angular/core';
import {PrivilegeService} from "../../../../../main/service/authentication/privilege.service";
import {Constants} from "../../../../../main/settings/constants";
import {AddEmployeeComponent} from "./add-employee/add-employee.component";
import {MatDialog} from "@angular/material/dialog";
import {EmployeeService} from "../../service/employee.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrls: ['./view-employee.component.scss']
})
export class ViewEmployeeComponent implements OnInit {

  adminPrivilege = Constants.privilegeCodes.ADMIN_DEFAULT_PRIVILEGE;

  displayedColumns: string[] = ['employeeID', 'name', 'nticNo', 'passportExpiryDateStr', 'phoneNumber', 'gender', 'status', 'actions'];
  dataSource: any = [];
  onGetAllEmployeesSubs = new Subscription();

  status = Constants.status;
  gender = Constants.gender;

  constructor(private privilegeService: PrivilegeService,
              private dialog: MatDialog,
              private employeeService: EmployeeService) {
  }

  ngOnInit(): void {
    this.employeeService.getAllEmployees();

    this.onGetAllEmployeesSubs = this.employeeService.onGetAllEmployees
      .subscribe((data: any) => {
        if (data) {
          this.dataSource = [];
          data.forEach((val: any) => {
            // @ts-ignore
            this.dataSource.push(val);
          });
        }
      });
  }

  isAdmin() {
    return this.privilegeService.hasPrivilege(this.adminPrivilege);
  };

  onCreateEmployee() {
    // @ts-ignore
    const dialogRef = this.dialog.open(AddEmployeeComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Add Employee`,
          type: 'ADD',
          employee: null
        }
      }
    );
  }

  view($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddEmployeeComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `View Employee`,
          type: 'VIEW',
          employee: row
        }
      }
    );
  }

  edit($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddEmployeeComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Edit Employee`,
          type: 'EDIT',
          employee: row
        }
      }
    );
  }

  remove($event: MouseEvent, row: any) {

  }
}
