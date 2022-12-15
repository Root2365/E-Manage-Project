export class UserDto {
  userID: any;
  userName: any;
  firstName: any;
  lastName: any;
  email: any;
  status: any;
  dateOfBirthStr: any;

  constructor(userDto?) {
    userDto = userDto || {};

    this.userID = userDto.userID || '';
    this.userName = userDto.userName || '';
    this.firstName = userDto.firstName || '';
    this.lastName = userDto.lastName || '';
    this.email = userDto.email || '';
    this.status = userDto.status || 'ACT';
    this.dateOfBirthStr = userDto.dateOfBirthStr || '';
  }
}
