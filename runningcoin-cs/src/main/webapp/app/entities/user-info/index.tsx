import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import UserInfo from './user-info';
import UserInfoDetail from './user-info-detail';
import UserInfoUpdate from './user-info-update';
import UserInfoDeleteDialog from './user-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <Route exact path={`${match.url}/new`} component={UserInfoUpdate} />
      <Route exact path={`${match.url}/:id/edit`} component={UserInfoUpdate} />
      <Route exact path={`${match.url}/:id`} component={UserInfoDetail} />
      <Route path={match.url} component={UserInfo} />
    </Switch>
    <Route path={`${match.url}/:id/delete`} component={UserInfoDeleteDialog} />
  </>
);

export default Routes;
