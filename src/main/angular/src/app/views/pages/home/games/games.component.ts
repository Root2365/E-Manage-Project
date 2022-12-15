import {Component, OnDestroy, OnInit} from '@angular/core';
import {PrivilegeService} from "../../../../main/service/authentication/privilege.service";
import {Constants} from "../../../../main/settings/constants";
import {AddGameComponent} from "./add-game/add-game.component";
import {MatDialog} from "@angular/material/dialog";
import {GamesService} from "../service/games.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.scss']
})
export class GamesComponent implements OnInit, OnDestroy {

  adminPrivilege = Constants.privilegeCodes.ADMIN_DEFAULT_PRIVILEGE;
  displayedColumns: string[] = ['gameID', 'picture', 'expiryDateStr', 'createdDateStr', 'status', 'actions'];
  dataSource: any = [];
  onGetAllGamesSubs = new Subscription();

  status = Constants.status;

  constructor(private privilegeService: PrivilegeService,
              private gamesService: GamesService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.onGetAllGamesSubs = this.gamesService.onGetAllGames
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

  ngOnDestroy(): void {
    this.onGetAllGamesSubs.unsubscribe();
  }

  isAdmin() {
    return this.privilegeService.hasPrivilege(this.adminPrivilege);
  };

  onCreateTopNewGame() {
    // @ts-ignore
    const dialogRef = this.dialog.open(AddGameComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Add Game`,
          type: 'ADD',
          game: null
        }
      }
    );
  }

  view($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddGameComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `View Game`,
          type: 'VIEW',
          game: row
        }
      }
    );
  }

  edit($event: MouseEvent, row: any) {
    const dialogRef = this.dialog.open(AddGameComponent, {
        panelClass: 'custom-dialog-panel',
        width: '70%',
        data: {
          title: `Edit Game`,
          type: 'EDIT',
          game: row
        }
      }
    );
  }

  remove($event: MouseEvent, row: any) {

  }
}
