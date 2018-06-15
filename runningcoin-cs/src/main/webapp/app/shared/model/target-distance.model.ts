import { Moment } from 'moment';

export interface ITargetDistance {
  id?: number;
  userGroupId?: number;
  creationTime?: Moment;
  targetDistance?: number;
}

export class TargetDistance implements ITargetDistance {
  constructor(public id?: number, public userGroupId?: number, public creationTime?: Moment, public targetDistance?: number) {}
}
