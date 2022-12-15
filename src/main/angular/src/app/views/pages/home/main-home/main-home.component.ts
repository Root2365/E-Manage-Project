import {Component, OnInit} from '@angular/core';
import {Constants} from "../../../../main/settings/constants";
import {PrivilegeService} from "../../../../main/service/authentication/privilege.service";

@Component({
  selector: 'app-main-home',
  templateUrl: './main-home.component.html',
  styleUrls: ['./main-home.component.scss']
})
export class MainHomeComponent implements OnInit {

  adminPrivilege = Constants.privilegeCodes.ADMIN_DEFAULT_PRIVILEGE;

  constructor(private privilegeService: PrivilegeService) {
  }

  ngOnInit(): void {

  }

  isAdmin() {
    return this.privilegeService.hasPrivilege(this.adminPrivilege);
  };
}
