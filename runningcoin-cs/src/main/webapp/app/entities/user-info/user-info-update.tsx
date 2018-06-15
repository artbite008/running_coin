import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './user-info.reducer';
import { IUserInfo } from 'app/shared/model/user-info.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IUserInfoUpdateProps {
  getEntity: ICrudGetAction<IUserInfo>;
  updateEntity: ICrudPutAction<IUserInfo>;
  createEntity: ICrudPutAction<IUserInfo>;
  userInfo: IUserInfo;
  reset: Function;
  loading: boolean;
  updating: boolean;
  match: any;
  history: any;
}

export interface IUserInfoUpdateState {
  isNew: boolean;
}

export class UserInfoUpdate extends React.Component<IUserInfoUpdateProps, IUserInfoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { userInfo } = this.props;
      const entity = {
        ...userInfo,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/user-info');
  };

  render() {
    const isInvalid = false;
    const { userInfo, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhi-user-info-heading">
              <Translate contentKey="runningcoinCsApp.userInfo.home.createOrEditLabel">Create or edit a UserInfo</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : userInfo} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="userNameLabel" for="userName">
                    <Translate contentKey="runningcoinCsApp.userInfo.userName">User Name</Translate>
                  </Label>
                  <AvField type="text" name="userName" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusFieldLabel" for="statusField">
                    <Translate contentKey="runningcoinCsApp.userInfo.statusField">Status Field</Translate>
                  </Label>
                  <AvField type="text" name="statusField" />
                </AvGroup>
                <AvGroup>
                  <Label id="roleLabel" for="role">
                    <Translate contentKey="runningcoinCsApp.userInfo.role">Role</Translate>
                  </Label>
                  <AvField type="text" name="role" />
                </AvGroup>
                <AvGroup>
                  <Label id="coinsLabel" for="coins">
                    <Translate contentKey="runningcoinCsApp.userInfo.coins">Coins</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="coins" />
                </AvGroup>
                <AvGroup>
                  <Label id="iconLabel" for="icon">
                    <Translate contentKey="runningcoinCsApp.userInfo.icon">Icon</Translate>
                  </Label>
                  <AvField type="text" name="icon" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalDistanceLabel" for="totalDistance">
                    <Translate contentKey="runningcoinCsApp.userInfo.totalDistance">Total Distance</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="totalDistance" />
                </AvGroup>
                <AvGroup>
                  <Label id="metaDataLabel" for="metaData">
                    <Translate contentKey="runningcoinCsApp.userInfo.metaData">Meta Data</Translate>
                  </Label>
                  <AvField type="text" name="metaData" />
                </AvGroup>
                <AvGroup>
                  <Label id="openIdLabel" for="openId">
                    <Translate contentKey="runningcoinCsApp.userInfo.openId">Open Id</Translate>
                  </Label>
                  <AvField type="text" name="openId" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/user-info" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={isInvalid || updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = storeState => ({
  userInfo: storeState.userInfo.entity,
  loading: storeState.userInfo.loading,
  updating: storeState.userInfo.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

export default connect(mapStateToProps, mapDispatchToProps)(UserInfoUpdate);
