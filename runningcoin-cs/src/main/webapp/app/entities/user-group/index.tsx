import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import UserGroup from './user-group';
import UserGroupDetail from './user-group-detail';
import UserGroupUpdate from './user-group-update';
import UserGroupDeleteDialog from './user-group-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <Route exact path={`${match.url}/new`} component={UserGroupUpdate} />
      <Route exact path={`${match.url}/:id/edit`} component={UserGroupUpdate} />
      <Route exact path={`${match.url}/:id`} component={UserGroupDetail} />
      <Route path={match.url} component={UserGroup} />
    </Switch>
    <Route path={`${match.url}/:id/delete`} component={UserGroupDeleteDialog} />
  </>
);

export default Routes;
