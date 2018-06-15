import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import GroupTable from './group-table';
import GroupTableDetail from './group-table-detail';
import GroupTableUpdate from './group-table-update';
import GroupTableDeleteDialog from './group-table-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <Route exact path={`${match.url}/new`} component={GroupTableUpdate} />
      <Route exact path={`${match.url}/:id/edit`} component={GroupTableUpdate} />
      <Route exact path={`${match.url}/:id`} component={GroupTableDetail} />
      <Route path={match.url} component={GroupTable} />
    </Switch>
    <Route path={`${match.url}/:id/delete`} component={GroupTableDeleteDialog} />
  </>
);

export default Routes;
