import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../main/service/authentication/auth.service";
import {ApplicationService} from "../../../main/service/application/application.service";
import {Router} from "@angular/router";
import {PrivilegeService} from "../../../main/service/authentication/privilege.service";
import {Constants} from "../../../main/settings/constants";
import {MatDialog} from "@angular/material/dialog";
import {ChangePasswordComponent} from "../../../views/pages/home/settings/change-password/change-password.component";
import {UserProfileComponent} from "../../../views/pages/home/settings/user-profile/user-profile.component";

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.scss']
})
export class BaseComponent implements OnInit {

  selfLayout: string;

  user: any;
  userType: any;

  adminPrivilege = Constants.privilegeCodes.ADMIN_DEFAULT_PRIVILEGE;

  constructor(private authService: AuthService,
              private applicationService: ApplicationService,
              private privilegeService: PrivilegeService,
              private dialog: MatDialog,
              private router: Router) {
  }

  ngOnInit(): void {
    this.user = this.applicationService.getLoggedInUser();
    if (this.user.isAdmin == 'Y') {
      this.userType = 'Admin'
    } else {
      this.userType = 'User'
    }
  }

  logout() {
    console.log('Logout');
    this.authService.setLoggedOut();
  }

  onClickHome() {
    this.router.navigate(['/home']);
  }

  isAdmin() {
    return this.privilegeService.hasPrivilege(this.adminPrivilege);
  };

  onClickChangePassword() {
    const dialogRef = this.dialog.open(ChangePasswordComponent, {
        panelClass: 'custom-dialog-panel',
        width: '40%',
        data: {
          userID: this.user.userID
        }
      }
    );
  }

  onClickProfile() {
    const dialogRef = this.dialog.open(UserProfileComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          userID: this.user.userID
        }
      }
    );
  }
}
