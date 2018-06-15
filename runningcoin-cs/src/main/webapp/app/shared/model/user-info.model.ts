export interface IUserInfo {
  id?: number;
  userName?: string;
  statusField?: string;
  role?: string;
  coins?: number;
  icon?: string;
  totalDistance?: number;
  metaData?: string;
  openId?: string;
}

export class UserInfo implements IUserInfo {
  constructor(
    public id?: number,
    public userName?: string,
    public statusField?: string,
    public role?: string,
    public coins?: number,
    public icon?: string,
    public totalDistance?: number,
    public metaData?: string,
    public openId?: string
  ) {}
}
