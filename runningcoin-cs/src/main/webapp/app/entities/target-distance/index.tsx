import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import TargetDistance from './target-distance';
import TargetDistanceDetail from './target-distance-detail';
import TargetDistanceUpdate from './target-distance-update';
import TargetDistanceDeleteDialog from './target-distance-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <Route exact path={`${match.url}/new`} component={TargetDistanceUpdate} />
      <Route exact path={`${match.url}/:id/edit`} component={TargetDistanceUpdate} />
      <Route exact path={`${match.url}/:id`} component={TargetDistanceDetail} />
      <Route path={match.url} component={TargetDistance} />
    </Switch>
    <Route path={`${match.url}/:id/delete`} component={TargetDistanceDeleteDialog} />
  </>
);

export default Routes;
