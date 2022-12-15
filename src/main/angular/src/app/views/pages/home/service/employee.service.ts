import {Injectable} from '@angular/core';
import {DataService} from "../../../../main/service/data/data.service";
import {Subject} from "rxjs";
import {SETTINGS} from "../../../../main/settings/commons.settings";

@Injectable()
export class EmployeeService {

  selectedEmployee: any;
  onEmployeeSaveOrUpdate: Subject<any> = new Subject();
  onGetAllEmployees: Subject<any> = new Subject();

  constructor(private dataService: DataService) {
  }

  getAllEmployees() {
    let response = this.dataService.get(SETTINGS.ENDPOINTS.getAllEmployees);
    response.subscribe((data: any) => {
      this.onGetAllEmployees.next(data);
    });
  }

  saveOrUpdateEmployee(saveRQ) {
    this.dataService.post(SETTINGS.ENDPOINTS.saveOrUpdateEmployee, saveRQ)
      .subscribe(response => {
        this.selectedEmployee = response;
        this.onEmployeeSaveOrUpdate.next(this.selectedEmployee);
      });
  }
}
