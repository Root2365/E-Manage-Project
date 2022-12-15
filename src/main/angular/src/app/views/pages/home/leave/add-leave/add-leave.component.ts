import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DateService} from "../../../../../main/service/application/date.service";
import {LeaveService} from "../../service/leave.service";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material/core";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import * as moment from "moment";
import {SETTINGS} from "../../../../../main/settings/commons.settings";
import {LeaveDto} from "../../model/leave-dto";
import {Subscription} from "rxjs";
import {EmployeeService} from "../../service/employee.service";

export const CUSTOM_DATE_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'DD/MM/YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'DD/MM/YYYY',
  },
};

@Component({
  selector: 'app-add-leave',
  templateUrl: './add-leave.component.html',
  styleUrls: ['./add-leave.component.scss'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS}
  ]
})
export class AddLeaveComponent implements OnInit, OnDestroy {

  title: any;
  type: any;
  addLeaveForm!: FormGroup;
  leaveDto = new LeaveDto();
  onLeaveSaveSubs = new Subscription();

  allEmployees: any = [];

  onGetAllEmployeesSubs = new Subscription();

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<AddLeaveComponent>,
              private formBuilder: FormBuilder,
              private dateService: DateService,
              private employeeService: EmployeeService,
              private leaveService: LeaveService) {
  }

  ngOnDestroy(): void {
    this.onGetAllEmployeesSubs.unsubscribe();
    this.onLeaveSaveSubs.unsubscribe();
  }

  ngOnInit(): void {
    this.employeeService.getAllEmployees();

    this.title = this.data.title;
    this.type = this.data.type;

    this.leaveDto = new LeaveDto(this.data.leave);

    this.initLeaveForm();

    this.onGetAllEmployeesSubs = this.employeeService.onGetAllEmployees
      .subscribe((data: any) => {
        if (data) {
          this.allEmployees = [];
          data.forEach((val: any) => {
            // @ts-ignore
            this.allEmployees.push(val);
          });
        }
      });

    this.onLeaveSaveSubs = this.leaveService.onLeaveSave
      .subscribe(data => {
        if (data) {
          if (this.type == 'Add') {
            alert("Leave saved successfully");
          } else {
            alert("Leave updated successfully");
          }

          this.onCancelClick(null);
          this.leaveService.getAllLeaves();
        }
      });
  }

  initLeaveForm() {
    this.addLeaveForm = this.formBuilder.group({
      employeeID: [this.leaveDto.employeeID, Validators.compose([
        Validators.required
      ])],
      reason: [this.leaveDto.reason, Validators.compose([
        Validators.required
      ])],
      fromDateStr: [this.dateService.getMomentDateFromDateStr(this.leaveDto.fromDateStr), Validators.compose([
        Validators.required
      ])],
      toDateStr: [this.dateService.getMomentDateFromDateStr(this.leaveDto.toDateStr), Validators.compose([
        Validators.required
      ])],
      leaveStatus: [this.leaveDto.leaveStatus, Validators.compose([
        Validators.required
      ])]
    });
  }

  isControlHasError(controlName: string, validationType: string): boolean {
    const control = this.addLeaveForm.controls[controlName];
    if (!control) {
      return false;
    }

    const result = control.hasError(validationType) && (control.dirty || control.touched);
    return result;
  }

  onCancelClick($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    this.dialogRef.close(false);
  }

  onSave($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    let submitData = Object.assign({}, this.addLeaveForm.getRawValue());
    submitData.fromDateStr = moment(submitData.fromDateStr).format(SETTINGS.DATE_TIME.DEFAULT_DATE_FORMAT);
    submitData.toDateStr = moment(submitData.toDateStr).format(SETTINGS.DATE_TIME.DEFAULT_DATE_FORMAT);

    if (this.type === 'EDIT') {
      submitData.leaveID = this.leaveDto.leaveID;
    } else {
      submitData.leaveID = null;
    }

    this.leaveService.saveOrUpdateLeave(submitData);
  }

  isValidForm() {
    return this.addLeaveForm.valid;
  }
}
