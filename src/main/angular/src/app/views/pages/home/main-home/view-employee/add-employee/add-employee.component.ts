import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import * as moment from "moment";
import {SETTINGS} from "../../../../../../main/settings/commons.settings";
import {DateService} from "../../../../../../main/service/application/date.service";
import {EmployeeDto} from "../../../model/employee-dto";
import {EmployeeService} from "../../../service/employee.service";
import {Subscription} from "rxjs";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material/core";
import {MomentDateAdapter} from "@angular/material-moment-adapter";

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
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.scss'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS}
  ]
})
export class AddEmployeeComponent implements OnInit, OnDestroy {

  title: any;
  type: any;
  addNewEmployeeForm!: FormGroup;
  employeeDto = new EmployeeDto();
  onEmployeeSaveOrUpdateSubs = new Subscription();

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<AddEmployeeComponent>,
              private formBuilder: FormBuilder,
              private employeeService: EmployeeService,
              private dateService: DateService) {
  }

  ngOnInit(): void {
    this.title = this.data.title;
    this.type = this.data.type;
    this.employeeDto = new EmployeeDto(this.data.employee);

    this.initAddNewEmployeeForm();

    this.onEmployeeSaveOrUpdateSubs = this.employeeService.onEmployeeSaveOrUpdate
      .subscribe(data => {
        if (data) {
          if (this.type == 'Add') {
            alert("Employee saved successfully");
          } else {
            alert("Employee updated successfully");
          }

          this.onCancelClick(null);
          this.employeeService.getAllEmployees();
        }
      });
  }

  initAddNewEmployeeForm() {
    this.addNewEmployeeForm = this.formBuilder.group({
      name: [this.employeeDto.name, Validators.compose([
        Validators.required
      ])],
      nticNo: [this.employeeDto.nticNo, Validators.compose([
        Validators.required
      ])],
      address: [this.employeeDto.address, Validators.compose([
        Validators.required
      ])],
      seatLocation: [this.employeeDto.seatLocation, Validators.compose([
        Validators.required
      ])],
      workInformation: [this.employeeDto.workInformation, Validators.compose([
        Validators.required
      ])],
      phoneNumber: [this.employeeDto.phoneNumber, Validators.compose([
        Validators.required
      ])],
      passportExpiryDateStr: [this.dateService.getMomentDateFromDateStr(this.employeeDto.passportExpiryDateStr), Validators.compose([
        Validators.required
      ])],
      gender: [this.employeeDto.gender, Validators.compose([
        Validators.required
      ])],
      email: [this.employeeDto.email, Validators.compose([
        Validators.required
      ])],
      department: [this.employeeDto.department, Validators.compose([
        Validators.required
      ])],
      salary: [this.employeeDto.salary, Validators.compose([
        Validators.required
      ])]
    });
  }

  isControlHasError(controlName: string, validationType: string): boolean {
    const control = this.addNewEmployeeForm.controls[controlName];
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
    let submitData = Object.assign({}, this.addNewEmployeeForm.getRawValue());
    submitData.passportExpiryDateStr = moment(submitData.passportExpiryDateStr).format(SETTINGS.DATE_TIME.DEFAULT_DATE_FORMAT);

    if (this.type === 'EDIT') {
      submitData.employeeID = this.employeeDto.employeeID;
    } else {
      submitData.employeeID = null;
    }

    submitData.photoURL = null;

    console.log(submitData);

    this.employeeService.saveOrUpdateEmployee(submitData);
  }

  isValidForm() {
    return this.addNewEmployeeForm.valid;
  }

  ngOnDestroy(): void {
    this.onEmployeeSaveOrUpdateSubs.unsubscribe();
  }
}
