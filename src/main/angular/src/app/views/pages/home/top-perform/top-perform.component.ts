import {Component, OnDestroy, OnInit} from '@angular/core';
import {ApplicationService} from "../../../../main/service/application/application.service";
import {Constants} from "../../../../main/settings/constants";
import {PrivilegeService} from "../../../../main/service/authentication/privilege.service";
import {MatDialog} from "@angular/material/dialog";
import {AddTopPerformerComponent} from "./add-top-performer/add-top-performer.component";
import {TopPerformerService} from "../service/top-performer.service";
import {Subscription} from "rxjs";
import {RemoveTopPerformerComponent} from "./remove-top-performer/remove-top-performer.component";

@Component({
  selector: 'app-top-perform',
  templateUrl: './top-perform.component.html',
  styleUrls: ['./top-perform.component.scss']
})
export class TopPerformComponent implements OnInit, OnDestroy {

  displayedColumns: string[] = ['topPerformerID', 'employeeName', 'picture', 'reason', 'createdDateStr', 'actions'];
  dataSource: any = [];

  onGetAllTopPerformersSubs = new Subscription();

  adminPrivilege = Constants.privilegeCodes.ADMIN_DEFAULT_PRIVILEGE;

  constructor(private applicationService: ApplicationService,
              private privilegeService: PrivilegeService,
              private dialog: MatDialog,
              private topPerformerService: TopPerformerService) {
  }

  ngOnDestroy(): void {
    this.onGetAllTopPerformersSubs.unsubscribe();
  }

  ngOnInit(): void {

    this.onGetAllTopPerformersSubs = this.topPerformerService.onGetAllTopPerformers
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

  onCreateTopNewTopPerformer() {
    // @ts-ignore
    const dialogRef = this.dialog.open(AddTopPerformerComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Add Top Performer`,
          type: 'ADD',
          topPerformer: null
        }
      }
    );
  }

  view($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddTopPerformerComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `View Top Performer`,
          type: 'VIEW',
          topPerformer: row
        }
      }
    );
  }

  edit($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddTopPerformerComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Edit Top Performer`,
          type: 'EDIT',
          topPerformer: row
        }
      }
    );
  }

  remove($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(RemoveTopPerformerComponent, {
        panelClass: 'custom-dialog-panel',
        width: '50%',
        data: {
          title: `Remove Top Performer`,
          type: 'REMOVE',
          topPerformer: row
        }
      }
    );
  }
}
