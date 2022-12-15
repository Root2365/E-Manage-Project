import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Observable, Subject} from "rxjs";
import {SETTINGS} from "../../../../main/settings/commons.settings";
import {DataService} from "../../../../main/service/data/data.service";

@Injectable()
export class TopPerformerService {

  selectedTopPerformer: any;
  onTopPerformerSave: Subject<any> = new Subject();
  onGetAllTopPerformers: Subject<any> = new Subject();
  onTopPerformerRemove: Subject<any> = new Subject();

  constructor(private dataService: DataService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {

    return new Promise<void>((resolve, reject) => {

      Promise.all([
        this.getAllTopPerformers(),
      ]).then(
        () => {
          resolve();
        },
        reject
      );
    });
  }

  getAllTopPerformers() {
    let response = this.dataService.get(SETTINGS.ENDPOINTS.getAllTopPerformers);
    response.subscribe((data: any) => {
      this.onGetAllTopPerformers.next(data);
    });
  }

  saveOrUpdateTopPerformer(saveRQ) {
    this.dataService.post(SETTINGS.ENDPOINTS.saveOrUpdateTopPerformer, saveRQ)
      .subscribe(response => {
        this.selectedTopPerformer = response;
        this.onTopPerformerSave.next(this.selectedTopPerformer);
      });
  }

  removeTopPerformer(topPerformerID) {
    const data = Object.assign({}, SETTINGS.ENDPOINTS.removeTopPerformer);
    data.url = data.url + '/' + topPerformerID;

    this.dataService.get(data)
      .subscribe((response: any) => {
        this.onTopPerformerRemove.next(response);
      });
  }
}
