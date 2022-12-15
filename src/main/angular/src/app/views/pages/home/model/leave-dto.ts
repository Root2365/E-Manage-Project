export class LeaveDto {
  leaveID: any;
  employeeID: any;
  reason: any;
  fromDateStr: any;
  toDateStr: any;
  leaveStatus: any;

  constructor(leaveDto?) {
    leaveDto = leaveDto || {};

    this.leaveID = leaveDto.leaveID || '';
    this.employeeID = leaveDto.employeeID || '';
    this.reason = leaveDto.reason || '';
    this.fromDateStr = leaveDto.fromDateStr || '';
    this.toDateStr = leaveDto.toDateStr || '';
    this.leaveStatus = leaveDto.leaveStatus || '';
  }
}
