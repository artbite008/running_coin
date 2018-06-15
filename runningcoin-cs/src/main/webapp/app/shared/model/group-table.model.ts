export interface IGroupTable {
  id?: number;
  groupName?: string;
  metaData?: string;
}

export class GroupTable implements IGroupTable {
  constructor(public id?: number, public groupName?: string, public metaData?: string) {}
}
