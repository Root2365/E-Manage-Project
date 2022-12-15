export class ContactUsDto {
  name: any;
  fromEmail: any;
  toEmail: any;
  subject: any;
  message: any;

  constructor(contactUsDto?) {
    contactUsDto = contactUsDto || {};

    this.name = contactUsDto.name || '';
    this.fromEmail = contactUsDto.fromEmail || '';
    this.toEmail = contactUsDto.toEmail || '';
    this.subject = contactUsDto.subject || '';
    this.message = contactUsDto.message || '';
  }
}
