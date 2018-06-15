import * as React from 'react';
// tslint:disable-next-line:no-unused-variable
import { Route, Switch } from 'react-router-dom';

import GroupTable from './group-table';
import RunningRecord from './running-record';
import VoteRecord from './vote-record';
import UserInfo from './user-info';
import UserGroup from './user-group';
import TargetDistance from './target-distance';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <Route path={`${match.url}/group-table`} component={GroupTable} />
      <Route path={`${match.url}/running-record`} component={RunningRecord} />
      <Route path={`${match.url}/vote-record`} component={VoteRecord} />
      <Route path={`${match.url}/user-info`} component={UserInfo} />
      <Route path={`${match.url}/user-group`} component={UserGroup} />
      <Route path={`${match.url}/target-distance`} component={TargetDistance} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
