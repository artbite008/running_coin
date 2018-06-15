import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity } from './user-group.reducer';
import { IUserGroup } from 'app/shared/model/user-group.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserGroupDetailProps {
  getEntity: ICrudGetAction<IUserGroup>;
  userGroup: IUserGroup;
  match: any;
}

export class UserGroupDetail extends React.Component<IUserGroupDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { userGroup } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="runningcoinCsApp.userGroup.detail.title">UserGroup</Translate> [<b>{userGroup.id}</b>]
          </h2>
          <Row size="md">
            <dl className="jh-entity-details">
              <dt>
                <span id="userOpenid">
                  <Translate contentKey="runningcoinCsApp.userGroup.userOpenid">User Openid</Translate>
                </span>
              </dt>
              <dd>{userGroup.userOpenid}</dd>
              <dt>
                <span id="groupId">
                  <Translate contentKey="runningcoinCsApp.userGroup.groupId">Group Id</Translate>
                </span>
              </dt>
              <dd>{userGroup.groupId}</dd>
            </dl>
          </Row>
          <Button tag={Link} to="/entity/user-group" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          <Button tag={Link} to={`/entity/user-group/${userGroup.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ userGroup }) => ({
  userGroup: userGroup.entity
});

const mapDispatchToProps = { getEntity };

export default connect(mapStateToProps, mapDispatchToProps)(UserGroupDetail);
