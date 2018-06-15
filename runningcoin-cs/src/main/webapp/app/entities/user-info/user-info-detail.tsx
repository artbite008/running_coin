import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity } from './user-info.reducer';
import { IUserInfo } from 'app/shared/model/user-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserInfoDetailProps {
  getEntity: ICrudGetAction<IUserInfo>;
  userInfo: IUserInfo;
  match: any;
}

export class UserInfoDetail extends React.Component<IUserInfoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { userInfo } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="runningcoinCsApp.userInfo.detail.title">UserInfo</Translate> [<b>{userInfo.id}</b>]
          </h2>
          <Row size="md">
            <dl className="jh-entity-details">
              <dt>
                <span id="userName">
                  <Translate contentKey="runningcoinCsApp.userInfo.userName">User Name</Translate>
                </span>
              </dt>
              <dd>{userInfo.userName}</dd>
              <dt>
                <span id="statusField">
                  <Translate contentKey="runningcoinCsApp.userInfo.statusField">Status Field</Translate>
                </span>
              </dt>
              <dd>{userInfo.statusField}</dd>
              <dt>
                <span id="role">
                  <Translate contentKey="runningcoinCsApp.userInfo.role">Role</Translate>
                </span>
              </dt>
              <dd>{userInfo.role}</dd>
              <dt>
                <span id="coins">
                  <Translate contentKey="runningcoinCsApp.userInfo.coins">Coins</Translate>
                </span>
              </dt>
              <dd>{userInfo.coins}</dd>
              <dt>
                <span id="icon">
                  <Translate contentKey="runningcoinCsApp.userInfo.icon">Icon</Translate>
                </span>
              </dt>
              <dd>{userInfo.icon}</dd>
              <dt>
                <span id="totalDistance">
                  <Translate contentKey="runningcoinCsApp.userInfo.totalDistance">Total Distance</Translate>
                </span>
              </dt>
              <dd>{userInfo.totalDistance}</dd>
              <dt>
                <span id="metaData">
                  <Translate contentKey="runningcoinCsApp.userInfo.metaData">Meta Data</Translate>
                </span>
              </dt>
              <dd>{userInfo.metaData}</dd>
              <dt>
                <span id="openId">
                  <Translate contentKey="runningcoinCsApp.userInfo.openId">Open Id</Translate>
                </span>
              </dt>
              <dd>{userInfo.openId}</dd>
            </dl>
          </Row>
          <Button tag={Link} to="/entity/user-info" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          <Button tag={Link} to={`/entity/user-info/${userInfo.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ userInfo }) => ({
  userInfo: userInfo.entity
});

const mapDispatchToProps = { getEntity };

export default connect(mapStateToProps, mapDispatchToProps)(UserInfoDetail);
