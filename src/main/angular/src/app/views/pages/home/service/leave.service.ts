import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Observable, Subject} from "rxjs";
import {SETTINGS} from "../../../../main/settings/commons.settings";
import {DataService} from "../../../../main/service/data/data.service";

@Injectable()
export class LeaveService {

  selectedLeave: any;
  onLeaveSave: Subject<any> = new Subject();
  onGetAllLeaves: Subject<any> = new Subject();

  constructor(private dataService: DataService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {

    return new Promise<void>((resolve, reject) => {

      Promise.all([
        this.getAllLeaves(),
      ]).then(
        () => {
          resolve();
        },
        reject
      );
    });
  }

  getAllLeaves() {
    let response = this.dataService.get(SETTINGS.ENDPOINTS.getAllLeaves);
    response.subscribe((data: any) => {
      this.onGetAllLeaves.next(data);
    });
  }

  saveOrUpdateLeave(saveRQ) {
    this.dataService.post(SETTINGS.ENDPOINTS.saveOrUpdateLeave, saveRQ)
      .subscribe(response => {
        this.selectedLeave = response;
        this.onLeaveSave.next(this.selectedLeave);
      });
  }
}
