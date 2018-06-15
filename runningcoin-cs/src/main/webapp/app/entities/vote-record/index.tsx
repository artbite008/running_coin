import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import VoteRecord from './vote-record';
import VoteRecordDetail from './vote-record-detail';
import VoteRecordUpdate from './vote-record-update';
import VoteRecordDeleteDialog from './vote-record-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <Route exact path={`${match.url}/new`} component={VoteRecordUpdate} />
      <Route exact path={`${match.url}/:id/edit`} component={VoteRecordUpdate} />
      <Route exact path={`${match.url}/:id`} component={VoteRecordDetail} />
      <Route path={match.url} component={VoteRecord} />
    </Switch>
    <Route path={`${match.url}/:id/delete`} component={VoteRecordDeleteDialog} />
  </>
);

export default Routes;
