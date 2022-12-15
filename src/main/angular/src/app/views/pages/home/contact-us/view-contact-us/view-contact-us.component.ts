import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ContactUsDto} from "../../model/contact-us-dto";
import {EnquiryService} from "../../service/enquiry.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-view-contact-us',
  templateUrl: './view-contact-us.component.html',
  styleUrls: ['./view-contact-us.component.scss']
})
export class ViewContactUsComponent implements OnInit, OnDestroy {

  addEnquiryForm!: FormGroup;
  contactUsDto = new ContactUsDto();
  onEnquirySaveSubs = new Subscription();

  constructor(private formBuilder: FormBuilder,
              private enquiryService: EnquiryService) {
  }

  ngOnInit(): void {
    this.initForm();

    this.onEnquirySaveSubs = this.enquiryService.onEnquirySave
      .subscribe(data => {
        if (data) {
          alert("Enquiry added successfully");

          this.initForm();
        }
      });
  }

  isControlHasError(controlName: string, validationType: string): boolean {
    const control = this.addEnquiryForm.controls[controlName];
    if (!control) {
      return false;
    }

    const result = control.hasError(validationType) && (control.dirty || control.touched);
    return result;
  }

  initForm() {
    this.addEnquiryForm = this.formBuilder.group({
      name: [this.contactUsDto.name, Validators.compose([
        Validators.required
      ])],
      fromEmail: [this.contactUsDto.fromEmail, Validators.compose([
        Validators.required
      ])],
      toEmail: [this.contactUsDto.toEmail, Validators.compose([
        Validators.required
      ])],
      subject: [this.contactUsDto.subject, Validators.compose([
        Validators.required
      ])],
      message: [this.contactUsDto.message, Validators.compose([
        Validators.required
      ])]
    });
  }

  isValidForm() {
    return this.addEnquiryForm.valid;
  }

  onSave($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    let submitData = Object.assign({}, this.addEnquiryForm.getRawValue());

    if (this.validateEmail(submitData.fromEmail) == null) {
      alert("From email is not valid");
      return;
    }

    if (this.validateEmail(submitData.toEmail) == null) {
      alert("To email is not valid");
      return;
    }

    this.enquiryService.saveEnquiry(submitData);
  }

  validateEmail(email) {
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
  };

  onReset($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    this.initForm();
  }

  ngOnDestroy(): void {
    this.onEnquirySaveSubs.unsubscribe();
  }
}
