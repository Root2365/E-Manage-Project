import {Injectable} from '@angular/core';
import {SETTINGS} from "../../settings/commons.settings";
import {EncryptionService} from "../application/encryption.service";
import {LocalStorage} from "ngx-webstorage";
import * as _ from 'underscore';

@Injectable()
export class PrivilegeService {

  @LocalStorage(SETTINGS.USER_PRIVILEGES)
  private userPrivilegesEncStr: string;

  private userPrivileges: Array<string> = [];

  constructor(private encryptionService: EncryptionService) {
    if (!_.isEmpty(this.userPrivilegesEncStr)) {
      this.decryptPrivileges();
    }
  }

  setUserPrivileges(userPrivileges: Array<string>) {
    this.userPrivilegesEncStr = this.encryptionService.encrypt(JSON.stringify(userPrivileges));
    this.decryptPrivileges();
  }

  hasPrivilege(privilegeCode: string): boolean {
    return _.contains(this.userPrivileges, privilegeCode);
  }

  hasAnyPrivilege(privilegeCodes: Array<string>): boolean {
    let hasAnyPrivilege = false;

    for (let i = 0; i < privilegeCodes.length; i++) {
      if (this.hasPrivilege(privilegeCodes[i])) {
        hasAnyPrivilege = true;
        break;
      }
    }

    return hasAnyPrivilege;
  }

  private decryptPrivileges() {
    this.userPrivileges = JSON.parse(this.encryptionService.decrypt(this.userPrivilegesEncStr));
  }
}
