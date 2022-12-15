export class TopPerformerDto {

  topPerformerID: any;
  employeeName: any;
  picture: any;
  reason: any;
  createdDateStr: any;

  constructor(topPerformerDto?) {
    topPerformerDto = topPerformerDto || {};

    this.topPerformerID = topPerformerDto.topPerformerID || '';
    this.employeeName = topPerformerDto.employeeName || '';
    this.picture = topPerformerDto.picture || '';
    this.reason = topPerformerDto.reason || '';
    this.createdDateStr = topPerformerDto.createdDateStr || '';
  }
}
