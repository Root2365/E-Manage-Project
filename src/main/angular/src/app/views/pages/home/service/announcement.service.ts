import {Injectable} from '@angular/core';
import {DataService} from "../../../../main/service/data/data.service";
import {ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Observable, Subject} from "rxjs";
import {SETTINGS} from "../../../../main/settings/commons.settings";

@Injectable()
export class AnnouncementService {

  selectedAnnouncement: any;
  onAnnouncementSaveUpdate: Subject<any> = new Subject();
  onGetAllAnnouncements: Subject<any> = new Subject();

  constructor(private dataService: DataService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {

    return new Promise<void>((resolve, reject) => {

      Promise.all([
        this.getAllAnnouncements(),
      ]).then(
        () => {
          resolve();
        },
        reject
      );
    });
  }

  getAllAnnouncements() {
    let response = this.dataService.get(SETTINGS.ENDPOINTS.getAllAnnouncements);
    response.subscribe((data: any) => {
      this.onGetAllAnnouncements.next(data);
    });
  }

  saveOrUpdateAnnouncement(saveRQ) {
    this.dataService.post(SETTINGS.ENDPOINTS.saveOrUpdateAnnouncement, saveRQ)
      .subscribe(response => {
        this.selectedAnnouncement = response;
        this.onAnnouncementSaveUpdate.next(this.selectedAnnouncement);
      });
  }
}
