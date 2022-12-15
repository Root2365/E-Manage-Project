import {Injectable} from '@angular/core';
import {DataService} from "../../../../main/service/data/data.service";
import {SETTINGS} from "../../../../main/settings/commons.settings";
import {Subject} from "rxjs";

@Injectable()
export class EnquiryService {

  selectedEnquiry: any;
  onEnquirySave: Subject<any> = new Subject();

  constructor(private dataService: DataService) {
  }

  saveEnquiry(saveRQ) {
    this.dataService.post(SETTINGS.ENDPOINTS.saveEnquiry, saveRQ)
      .subscribe(response => {
        this.selectedEnquiry = response;
        this.onEnquirySave.next(this.selectedEnquiry);
      });
  }
}
