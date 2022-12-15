export class GameDto {
  gameID: any;
  picture: any;
  status: any;
  expiryDateStr: any;
  createdDateStr: any;

  constructor(gameDto?) {
    gameDto = gameDto || {};

    this.gameID = gameDto.gameID || '';
    this.picture = gameDto.picture || '';
    this.status = gameDto.status || 'ACT';
    this.expiryDateStr = gameDto.expiryDateStr || '';
    this.createdDateStr = gameDto.createdDateStr || '';
  }
}
