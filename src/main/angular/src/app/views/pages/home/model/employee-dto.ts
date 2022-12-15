export class EmployeeDto {
  employeeID: any;
  name: any;
  address: any;
  nticNo: any;
  status: any;
  passportExpiryDateStr: any;
  createdDateStr: any;
  seatLocation: any;
  workInformation: any;
  phoneNumber: any;
  gender: any;
  email: any;
  department: any;
  photoURL: any;
  salary: any;

  constructor(employeeDto?) {
    employeeDto = employeeDto || {};

    this.employeeID = employeeDto.employeeID || '';
    this.name = employeeDto.name || '';
    this.address = employeeDto.address || '';
    this.nticNo = employeeDto.nticNo || '';
    this.status = employeeDto.status || 'ACT';
    this.passportExpiryDateStr = employeeDto.passportExpiryDateStr || '';
    this.createdDateStr = employeeDto.createdDateStr || '';
    this.seatLocation = employeeDto.seatLocation || '';
    this.workInformation = employeeDto.workInformation || '';
    this.phoneNumber = employeeDto.phoneNumber || '';
    this.gender = employeeDto.gender || '';
    this.email = employeeDto.email || '';
    this.department = employeeDto.department || '';
    this.photoURL = employeeDto.photoURL || '';
    this.salary = employeeDto.salary || '';
  }
}
