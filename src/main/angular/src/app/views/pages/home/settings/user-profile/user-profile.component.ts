import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../../../main/service/authentication/auth.service";
import {UserService} from "../../service/user.service";
import {Subscription} from "rxjs";
import {UserDto} from "../../model/user-dto";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material/core";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {DateService} from "../../../../../main/service/application/date.service";
import * as moment from "moment";
import {SETTINGS} from "../../../../../main/settings/commons.settings";

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
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS}
  ]
})
export class UserProfileComponent implements OnInit, OnDestroy {

  userProfileForm!: FormGroup;
  userID: any;
  onGetUserSubs = new Subscription();
  onUserUpdateSubs = new Subscription();
  userDto = new UserDto();

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<UserProfileComponent>,
              private formBuilder: FormBuilder,
              private authService: AuthService,
              private dateService: DateService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.userID = this.data.userID;

    this.userService.getUserByID(this.userID);

    this.onGetUserSubs = this.userService.onGetUser
      .subscribe(data => {
        if (data) {
          this.userDto = new UserDto(data);
          this.initForm();
        }
      });

    this.onUserUpdateSubs = this.userService.onUserUpdate
      .subscribe(data => {
        if (data) {
          alert('User profile updated successfully');
          this.dialogRef.close(false);
        }
      });

    this.initForm();
  }

  initForm() {
    this.userProfileForm = this.formBuilder.group({
      userName: [this.userDto.userName, Validators.compose([
        Validators.required
      ])],
      firstName: [this.userDto.firstName, Validators.compose([
        Validators.required
      ])],
      lastName: [this.userDto.lastName, Validators.compose([
        Validators.required
      ])],
      email: [this.userDto.email, Validators.compose([
        Validators.required
      ])],
      dateOfBirthStr: [this.dateService.getMomentDateFromDateStr(this.userDto.dateOfBirthStr), Validators.compose([
        Validators.required
      ])]
    });
  }

  onCancelClick($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    this.dialogRef.close(false);
  }

  isControlHasError(controlName: string, validationType: string): boolean {
    const control = this.userProfileForm.controls[controlName];
    if (!control) {
      return false;
    }

    const result = control.hasError(validationType) && (control.dirty || control.touched);
    return result;
  }

  onUpdateUser($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    let submitData = Object.assign({}, this.userProfileForm.getRawValue());
    submitData.userID = this.userID;
    submitData.dateOfBirthStr = moment(submitData.dateOfBirthStr).format(SETTINGS.DATE_TIME.DEFAULT_DATE_FORMAT);

    this.userService.updateUser(submitData);
  }

  isFormValid() {
    return this.userProfileForm.valid;
  }

  ngOnDestroy(): void {
    this.onGetUserSubs.unsubscribe();
    this.onUserUpdateSubs.unsubscribe();
  }
}
