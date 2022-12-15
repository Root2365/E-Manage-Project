import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TopPerformerService} from "../../service/top-performer.service";
import {TopPerformerDto} from "../../model/top-performer-dto";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-remove-top-performer',
  templateUrl: './remove-top-performer.component.html',
  styleUrls: ['./remove-top-performer.component.scss']
})
export class RemoveTopPerformerComponent implements OnInit, OnDestroy {

  topPerformerDto = new TopPerformerDto();
  onTopPerformerRemoveSubs = new Subscription();

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<RemoveTopPerformerComponent>,
              private topPerformerService: TopPerformerService) {
  }

  ngOnInit(): void {
    this.topPerformerDto = new TopPerformerDto(this.data.topPerformer);

    this.onTopPerformerRemoveSubs = this.topPerformerService.onTopPerformerRemove
      .subscribe(data => {
        if (data) {
          alert("Top performer removed successfully");

          this.onCancelClick(null);
          this.topPerformerService.getAllTopPerformers();
        }
      });
  }

  onCancelClick($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    this.dialogRef.close(false);
  }

  onRemove($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    let topPerformerID = this.topPerformerDto.topPerformerID;
    this.topPerformerService.removeTopPerformer(topPerformerID);
  }

  ngOnDestroy(): void {
    this.onTopPerformerRemoveSubs.unsubscribe();
  }
}
