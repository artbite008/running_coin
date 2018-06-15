import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import RunningRecord from './running-record';
import RunningRecordDetail from './running-record-detail';
import RunningRecordUpdate from './running-record-update';
import RunningRecordDeleteDialog from './running-record-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <Route exact path={`${match.url}/new`} component={RunningRecordUpdate} />
      <Route exact path={`${match.url}/:id/edit`} component={RunningRecordUpdate} />
      <Route exact path={`${match.url}/:id`} component={RunningRecordDetail} />
      <Route path={match.url} component={RunningRecord} />
    </Switch>
    <Route path={`${match.url}/:id/delete`} component={RunningRecordDeleteDialog} />
  </>
);

export default Routes;
