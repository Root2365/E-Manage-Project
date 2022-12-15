import {Component, OnDestroy, OnInit} from '@angular/core';
import {Constants} from "../../../../main/settings/constants";
import {Subscription} from "rxjs";
import {PrivilegeService} from "../../../../main/service/authentication/privilege.service";
import {MatDialog} from "@angular/material/dialog";
import {LeaveService} from "../service/leave.service";
import {AddLeaveComponent} from "./add-leave/add-leave.component";

@Component({
  selector: 'app-leave',
  templateUrl: './leave.component.html',
  styleUrls: ['./leave.component.scss']
})
export class LeaveComponent implements OnInit, OnDestroy {

  adminPrivilege = Constants.privilegeCodes.ADMIN_DEFAULT_PRIVILEGE;
  displayedColumns: string[] = ['leaveID', 'employeeID', 'employeeName', 'fromDateStr', 'toDateStr', 'leaveStatus', 'actions'];
  dataSource: any = [];
  onGetAllLeavesSubs = new Subscription();

  leaveStatus = Constants.leaveStatus;

  constructor(private privilegeService: PrivilegeService,
              private leaveService: LeaveService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.onGetAllLeavesSubs = this.leaveService.onGetAllLeaves
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

  onAddLeave() {
    // @ts-ignore
    const dialogRef = this.dialog.open(AddLeaveComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Add Leave`,
          type: 'ADD',
          leave: null
        }
      }
    );
  }

  isAdmin() {
    return this.privilegeService.hasPrivilege(this.adminPrivilege);
  };

  view($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddLeaveComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `View Leave`,
          type: 'VIEW',
          leave: row
        }
      }
    );
  }

  edit($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddLeaveComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Edit Leave`,
          type: 'EDIT',
          leave: row
        }
      }
    );
  }

  remove($event: MouseEvent, row: any) {

  }

  ngOnDestroy(): void {
    this.onGetAllLeavesSubs.unsubscribe();
  }
}
