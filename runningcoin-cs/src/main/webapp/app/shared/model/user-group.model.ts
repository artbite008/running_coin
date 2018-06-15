export interface IUserGroup {
  id?: number;
  userOpenid?: string;
  groupId?: number;
}

export class UserGroup implements IUserGroup {
  constructor(public id?: number, public userOpenid?: string, public groupId?: number) {}
}
