import { Moment } from 'moment';

export interface IRunningRecord {
  id?: number;
  userGroupId?: number;
  distance?: number;
  creationTime?: Moment;
  lastVotedTime?: Moment;
  statusField?: number;
  score?: number;
  settledTime?: Moment;
  earnedCoins?: number;
  comments?: string;
  evidence?: string;
}

export class RunningRecord implements IRunningRecord {
  constructor(
    public id?: number,
    public userGroupId?: number,
    public distance?: number,
    public creationTime?: Moment,
    public lastVotedTime?: Moment,
    public statusField?: number,
    public score?: number,
    public settledTime?: Moment,
    public earnedCoins?: number,
    public comments?: string,
    public evidence?: string
  ) {}
}
