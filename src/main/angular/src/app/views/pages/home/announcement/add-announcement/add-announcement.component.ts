import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DateService} from "../../../../../main/service/application/date.service";
import {AnnouncementService} from "../../service/announcement.service";
import {AnnouncementDto} from "../../model/announcement-dto";

@Component({
  selector: 'app-add-announcement',
  templateUrl: './add-announcement.component.html',
  styleUrls: ['./add-announcement.component.scss']
})
export class AddAnnouncementComponent implements OnInit, OnDestroy {

  title: any;
  type: any;
  addAnnouncementForm!: FormGroup;
  announcementDto = new AnnouncementDto();
  onAnnouncementSaveUpdateSubs = new Subscription();

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<AddAnnouncementComponent>,
              private formBuilder: FormBuilder,
              private dateService: DateService,
              private announcementService: AnnouncementService) {
  }

  ngOnInit(): void {
    this.title = this.data.title;
    this.type = this.data.type;

    this.announcementDto = new AnnouncementDto(this.data.announcement);

    this.initAddAnnouncementForm();

    this.onAnnouncementSaveUpdateSubs = this.announcementService.onAnnouncementSaveUpdate
      .subscribe(data => {
        if (data) {
          if (this.type == 'Add') {
            alert("Announcement saved successfully");
          } else {
            alert("Announcement updated successfully");
          }

          this.onCancelClick(null);
          this.announcementService.getAllAnnouncements();
        }
      });
  }

  initAddAnnouncementForm() {
    this.addAnnouncementForm = this.formBuilder.group({
      title: [this.announcementDto.title, Validators.compose([
        Validators.required
      ])],
      status: [this.announcementDto.status, Validators.compose([
        Validators.required
      ])],
      description: [this.announcementDto.description, Validators.compose([
        Validators.required
      ])]
    });
  }

  ngOnDestroy(): void {
    this.onAnnouncementSaveUpdateSubs.unsubscribe();
  }

  onCancelClick($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    this.dialogRef.close(false);
  }

  isControlHasError(controlName: string, validationType: string): boolean {
    const control = this.addAnnouncementForm.controls[controlName];
    if (!control) {
      return false;
    }

    const result = control.hasError(validationType) && (control.dirty || control.touched);
    return result;
  }

  onSave($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    let submitData = Object.assign({}, this.addAnnouncementForm.getRawValue());

    if (this.type === 'EDIT') {
      submitData.announcementID = this.announcementDto.announcementID;
    } else {
      submitData.announcementID = null;
    }

    this.announcementService.saveOrUpdateAnnouncement(submitData);
  }

  isValidForm() {
    return this.addAnnouncementForm.valid;
  }
}
