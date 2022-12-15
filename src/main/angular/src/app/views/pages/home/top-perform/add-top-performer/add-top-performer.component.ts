import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SETTINGS} from "../../../../../main/settings/commons.settings";
import * as moment from 'moment';
import {TopPerformerService} from "../../service/top-performer.service";
import {Subscription} from "rxjs";
import {Constants} from "../../../../../main/settings/constants";
import {PrivilegeService} from "../../../../../main/service/authentication/privilege.service";
import {TopPerformerDto} from "../../model/top-performer-dto";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material/core";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {DateService} from "../../../../../main/service/application/date.service";

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
  selector: 'app-add-top-performer',
  templateUrl: './add-top-performer.component.html',
  styleUrls: ['./add-top-performer.component.scss'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS}
  ]
})
export class AddTopPerformerComponent implements OnInit, OnDestroy {

  addNewTopPerformerForm!: FormGroup;
  onTopPerformerSaveSubs = new Subscription();
  title: any;
  type: any;
  adminPrivilege = Constants.privilegeCodes.ADMIN_DEFAULT_PRIVILEGE;
  topPerformerDto = new TopPerformerDto();

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<AddTopPerformerComponent>,
              private formBuilder: FormBuilder,
              private topPerformerService: TopPerformerService,
              private dateService: DateService,
              private privilegeService: PrivilegeService) {
  }

  ngOnInit(): void {
    this.title = this.data.title;
    this.type = this.data.type;

    this.topPerformerDto = new TopPerformerDto(this.data.topPerformer);

    this.initAddNewTopPerformerForm();

    this.onTopPerformerSaveSubs = this.topPerformerService.onTopPerformerSave
      .subscribe(data => {
        if (data) {
          if (this.type == 'Add') {
            alert("Top performer saved successfully");
          } else {
            alert("Top performer updated successfully");
          }

          this.onCancelClick(null);
          this.topPerformerService.getAllTopPerformers();
        }
      });
  }

  initAddNewTopPerformerForm() {
    this.addNewTopPerformerForm = this.formBuilder.group({
      employeeName: [this.topPerformerDto.employeeName, Validators.compose([
        Validators.required
      ])],
      picture: [this.topPerformerDto.picture, Validators.compose([
        Validators.required
      ])],
      reason: [this.topPerformerDto.reason, Validators.compose([
        Validators.required
      ])],
      dateStr: [this.dateService.getMomentDateFromDateStr(this.topPerformerDto.createdDateStr), Validators.compose([
        Validators.required
      ])]
    });
  }

  isControlHasError(controlName: string, validationType: string): boolean {
    const control = this.addNewTopPerformerForm.controls[controlName];
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
    let submitData = Object.assign({}, this.addNewTopPerformerForm.getRawValue());
    submitData.dateStr = moment(submitData.dateStr).format(SETTINGS.DATE_TIME.DEFAULT_DATE_FORMAT);

    if (this.type === 'EDIT') {
      submitData.topPerformerID = this.topPerformerDto.topPerformerID;
    } else {
      submitData.topPerformerID = null;
    }

    this.topPerformerService.saveOrUpdateTopPerformer(submitData);
  }

  isValidForm() {
    return this.addNewTopPerformerForm.valid;
  }

  ngOnDestroy(): void {
    this.onTopPerformerSaveSubs.unsubscribe();
  }

  isAdmin() {
    return this.privilegeService.hasPrivilege(this.adminPrivilege);
  };
}
