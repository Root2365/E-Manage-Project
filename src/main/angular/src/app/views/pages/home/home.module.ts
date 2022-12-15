import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HomeRoutingModule} from './home-routing.module';
import {MainHomeComponent} from './main-home/main-home.component';
import {TopPerformComponent} from './top-perform/top-perform.component';
import {SharedModule} from "../../../shared/shared.module";
import {AddTopPerformerComponent} from './top-perform/add-top-performer/add-top-performer.component';
import {TopPerformerService} from "./service/top-performer.service";
import {RemoveTopPerformerComponent} from './top-perform/remove-top-performer/remove-top-performer.component';
import {GamesComponent} from './games/games.component';
import {GamesService} from "./service/games.service";
import {AddGameComponent} from './games/add-game/add-game.component';
import {ViewEmployeeComponent} from './main-home/view-employee/view-employee.component';
import {AddEmployeeComponent} from './main-home/view-employee/add-employee/add-employee.component';
import {EmployeeService} from "./service/employee.service";
import {ViewAnnouncementComponent} from './announcement/view-announcement/view-announcement.component';
import {AnnouncementService} from "./service/announcement.service";
import {AddAnnouncementComponent} from './announcement/add-announcement/add-announcement.component';
import {LeaveComponent} from './leave/leave.component';
import {AddLeaveComponent} from './leave/add-leave/add-leave.component';
import {LeaveService} from "./service/leave.service";
import {ViewContactUsComponent} from './contact-us/view-contact-us/view-contact-us.component';
import {EnquiryService} from "./service/enquiry.service";
import { ChangePasswordComponent } from './settings/change-password/change-password.component';
import {UserService} from "./service/user.service";
import { UserProfileComponent } from './settings/user-profile/user-profile.component';

@NgModule({
  declarations: [
    MainHomeComponent,
    TopPerformComponent,
    AddTopPerformerComponent,
    RemoveTopPerformerComponent,
    GamesComponent,
    AddGameComponent,
    ViewEmployeeComponent,
    AddEmployeeComponent,
    ViewAnnouncementComponent,
    AddAnnouncementComponent,
    LeaveComponent,
    AddLeaveComponent,
    ViewContactUsComponent,
    ChangePasswordComponent,
    UserProfileComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule
  ],
  providers: [
    TopPerformerService,
    GamesService,
    EmployeeService,
    AnnouncementService,
    LeaveService,
    EnquiryService,
    UserService
  ]
})
export class HomeModule {
}
