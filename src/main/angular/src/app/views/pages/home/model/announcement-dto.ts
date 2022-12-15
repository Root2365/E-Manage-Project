export class AnnouncementDto {
  announcementID: any;
  title: any;
  status: any;
  description: any;
  createdDateStr: any;

  constructor(announcementDto?) {
    announcementDto = announcementDto || {};

    this.announcementID = announcementDto.announcementID || '';
    this.title = announcementDto.title || '';
    this.status = announcementDto.status || 'ACT';
    this.description = announcementDto.description || '';
  }
}
