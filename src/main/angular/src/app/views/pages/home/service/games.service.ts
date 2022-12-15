import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Observable, Subject} from "rxjs";
import {SETTINGS} from "../../../../main/settings/commons.settings";
import {DataService} from "../../../../main/service/data/data.service";

@Injectable()
export class GamesService {

  selectedGame: any;
  onGameSave: Subject<any> = new Subject();
  onGetAllGames: Subject<any> = new Subject();

  constructor(private dataService: DataService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {

    return new Promise<void>((resolve, reject) => {

      Promise.all([
        this.getAllGames(),
      ]).then(
        () => {
          resolve();
        },
        reject
      );
    });
  }

  getAllGames() {
    let response = this.dataService.get(SETTINGS.ENDPOINTS.getAllGames);
    response.subscribe((data: any) => {
      this.onGetAllGames.next(data);
    });
  }

  saveOrUpdateTopPerformer(saveRQ) {
    this.dataService.post(SETTINGS.ENDPOINTS.saveOrUpdateGame, saveRQ)
      .subscribe(response => {
        this.selectedGame = response;
        this.onGameSave.next(this.selectedGame);
      });
  }
}
