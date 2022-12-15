import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainHomeComponent} from "./main-home/main-home.component";
import {TopPerformComponent} from "./top-perform/top-perform.component";
import {TopPerformerService} from "./service/top-performer.service";
import {GamesComponent} from "./games/games.component";
import {GamesService} from "./service/games.service";
import {AnnouncementService} from "./service/announcement.service";
import {ViewAnnouncementComponent} from "./announcement/view-announcement/view-announcement.component";
import {LeaveService} from "./service/leave.service";
import {LeaveComponent} from "./leave/leave.component";
import {ViewContactUsComponent} from "./contact-us/view-contact-us/view-contact-us.component";

const routes: Routes = [
  {
    path: '',
    component: MainHomeComponent
  },
  {
    path: 'top-performers',
    resolve: {
      data: TopPerformerService

    },
    component: TopPerformComponent
  },
  {
    path: 'games',
    resolve: {
      data: GamesService

    },
    component: GamesComponent
  },
  {
    path: 'announcements',
    resolve: {
      data: AnnouncementService

    },
    component: ViewAnnouncementComponent
  },
  {
    path: 'leaves',
    resolve: {
      data: LeaveService

    },
    component: LeaveComponent
  },
  {
    path: 'contact-us',
    component: ViewContactUsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
