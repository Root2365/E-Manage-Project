import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material/core";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import * as moment from "moment";
import {SETTINGS} from "../../../../../main/settings/commons.settings";
import {GameDto} from "../../model/game-dto";
import {DateService} from "../../../../../main/service/application/date.service";
import {GamesService} from "../../service/games.service";
import {Subscription} from "rxjs";

export const CUSTOM_DATE_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'DD/MM/YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'DD/MM/YYYY',
  },
};

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrls: ['./add-game.component.scss'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS}
  ]
})
export class AddGameComponent implements OnInit, OnDestroy {

  title: any;
  type: any;
  addNewGameForm!: FormGroup;
  gameDto = new GameDto();
  onGameSaveSubs = new Subscription();

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<AddGameComponent>,
              private formBuilder: FormBuilder,
              private dateService: DateService,
              private gamesService: GamesService) {
  }

  ngOnInit(): void {
    this.title = this.data.title;
    this.type = this.data.type;

    this.gameDto = new GameDto(this.data.game);

    this.initAddNewGameForm();

    this.onGameSaveSubs = this.gamesService.onGameSave
      .subscribe(data => {
        if (data) {
          if (this.type == 'Add') {
            alert("Game saved successfully");
          } else {
            alert("Game updated successfully");
          }

          this.onCancelClick(null);
          this.gamesService.getAllGames();
        }
      });
  }

  ngOnDestroy(): void {
    this.onGameSaveSubs.unsubscribe();
  }

  initAddNewGameForm() {
    this.addNewGameForm = this.formBuilder.group({
      picture: [this.gameDto.picture, Validators.compose([
        Validators.required
      ])],
      expiryDateStr: [this.dateService.getMomentDateFromDateStr(this.gameDto.expiryDateStr), Validators.compose([
        Validators.required
      ])],
      status: [this.gameDto.status, Validators.compose([
        Validators.required
      ])]
    });
  }

  isControlHasError(controlName: string, validationType: string): boolean {
    const control = this.addNewGameForm.controls[controlName];
    if (!control) {
      return false;
    }

    const result = control.hasError(validationType) && (control.dirty || control.touched);
    return result;
  }

  onCancelClick($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    this.dialogRef.close(false);
  }

  onSave($event: MouseEvent) {
    if ($event) {
      $event.stopPropagation();
      $event.preventDefault();
    }

    let submitData = Object.assign({}, this.addNewGameForm.getRawValue());
    submitData.expiryDateStr = moment(submitData.expiryDateStr).format(SETTINGS.DATE_TIME.DEFAULT_DATE_FORMAT);

    if (this.type === 'EDIT') {
      submitData.gameID = this.gameDto.gameID;
    } else {
      submitData.gameID = null;
    }

    this.gamesService.saveOrUpdateTopPerformer(submitData);
  }

  isValidForm() {
    return this.addNewGameForm.valid;
  }
}
