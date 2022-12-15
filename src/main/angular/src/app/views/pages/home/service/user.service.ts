import {Injectable} from '@angular/core';
import {SETTINGS} from "../../../../main/settings/commons.settings";
import {Subject} from "rxjs";
import {DataService} from "../../../../main/service/data/data.service";

@Injectable()
export class UserService {

  selectedUser: any;
  onChangeUserPassword: Subject<any> = new Subject();
  onUserUpdate: Subject<any> = new Subject();
  onGetUser: Subject<any> = new Subject();

  constructor(private dataService: DataService) {
  }

  changeUserPassword(saveRQ) {
    this.dataService.post(SETTINGS.ENDPOINTS.changeUserPassword, saveRQ)
      .subscribe(response => {
        this.selectedUser = response;
        this.onChangeUserPassword.next(this.selectedUser);
      });
  }

  getUserByID(userID) {
    const data = Object.assign({}, SETTINGS.ENDPOINTS.getUserByID);
    data.url = data.url + '/' + userID;

    this.dataService.get(data)
      .subscribe((response: any) => {
        this.selectedUser = response;
        this.onGetUser.next(this.selectedUser);
      });
  }

  updateUser(updateRQ) {
    this.dataService.post(SETTINGS.ENDPOINTS.updateUser, updateRQ)
      .subscribe(response => {
        console.log(response);
        this.selectedUser = response;
        this.onUserUpdate.next(this.selectedUser);
      });
  }
}
