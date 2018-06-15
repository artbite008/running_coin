export interface IVoteRecord {
  id?: number;
  voteUserGroupId?: number;
  runingRecordId?: number;
  votedTime?: number;
  statusField?: number;
  score?: number;
  comments?: string;
}

export class VoteRecord implements IVoteRecord {
  constructor(
    public id?: number,
    public voteUserGroupId?: number,
    public runingRecordId?: number,
    public votedTime?: number,
    public statusField?: number,
    public score?: number,
    public comments?: string
  ) {}
}
