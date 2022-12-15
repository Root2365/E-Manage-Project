import {Component, OnDestroy, OnInit} from '@angular/core';
import {Constants} from "../../../../../main/settings/constants";
import {PrivilegeService} from "../../../../../main/service/authentication/privilege.service";
import {AddAnnouncementComponent} from "../add-announcement/add-announcement.component";
import {MatDialog} from "@angular/material/dialog";
import {AnnouncementService} from "../../service/announcement.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-view-announcement',
  templateUrl: './view-announcement.component.html',
  styleUrls: ['./view-announcement.component.scss']
})
export class ViewAnnouncementComponent implements OnInit, OnDestroy {

  adminPrivilege = Constants.privilegeCodes.ADMIN_DEFAULT_PRIVILEGE;
  displayedColumns: string[] = ['announcementID', 'title', 'description', 'createdDateStr', 'status', 'actions'];
  dataSource: any = [];
  onGetAllAnnouncementsSubs = new Subscription();

  status = Constants.status;

  constructor(private privilegeService: PrivilegeService,
              private dialog: MatDialog,
              private announcementService: AnnouncementService) {
  }

  ngOnInit(): void {
    this.onGetAllAnnouncementsSubs = this.announcementService.onGetAllAnnouncements
      .subscribe((data: any) => {
        if (data) {
          this.dataSource = [];
          data.forEach((val: any) => {
            // @ts-ignore
            this.dataSource.push(val);
          });
        }
      });
  }

  isAdmin() {
    return this.privilegeService.hasPrivilege(this.adminPrivilege);
  };

  onCreateAnnouncement() {
    // @ts-ignore
    const dialogRef = this.dialog.open(AddAnnouncementComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Add Announcement`,
          type: 'ADD',
          announcement: null
        }
      }
    );
  }

  edit($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddAnnouncementComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Edit Announcement`,
          type: 'EDIT',
          announcement: row
        }
      }
    );
  }

  view($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddAnnouncementComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `View Announcement`,
          type: 'VIEW',
          announcement: row
        }
      }
    );
  }

  ngOnDestroy(): void {
    this.onGetAllAnnouncementsSubs.unsubscribe();
  }
}
