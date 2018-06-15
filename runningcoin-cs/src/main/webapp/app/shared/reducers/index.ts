import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale from './locale';
import authentication from './authentication';
import applicationProfile from './application-profile';

import administration from 'app/modules/administration/administration.reducer';
import userManagement from 'app/modules/administration/user-management/user-management.reducer';
import register from 'app/modules/account/register/register.reducer';
import activate from 'app/modules/account/activate/activate.reducer';
import password from 'app/modules/account/password/password.reducer';
import settings from 'app/modules/account/settings/settings.reducer';
import passwordReset from 'app/modules/account/password-reset/password-reset.reducer';
import groupTable from 'app/entities/group-table/group-table.reducer';
import runningRecord from 'app/entities/running-record/running-record.reducer';
import voteRecord from 'app/entities/vote-record/vote-record.reducer';
import userInfo from 'app/entities/user-info/user-info.reducer';
import userGroup from 'app/entities/user-group/user-group.reducer';
import targetDistance from 'app/entities/target-distance/target-distance.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export default combineReducers({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  groupTable,
  runningRecord,
  voteRecord,
  userInfo,
  userGroup,
  targetDistance,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});
