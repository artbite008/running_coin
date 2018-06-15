import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './user-group.reducer';
import { IUserGroup } from 'app/shared/model/user-group.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IUserGroupUpdateProps {
  getEntity: ICrudGetAction<IUserGroup>;
  updateEntity: ICrudPutAction<IUserGroup>;
  createEntity: ICrudPutAction<IUserGroup>;
  userGroup: IUserGroup;
  reset: Function;
  loading: boolean;
  updating: boolean;
  match: any;
  history: any;
}

export interface IUserGroupUpdateState {
  isNew: boolean;
}

export class UserGroupUpdate extends React.Component<IUserGroupUpdateProps, IUserGroupUpdateState> {
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
      const { userGroup } = this.props;
      const entity = {
        ...userGroup,
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
    this.props.history.push('/entity/user-group');
  };

  render() {
    const isInvalid = false;
    const { userGroup, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhi-user-group-heading">
              <Translate contentKey="runningcoinCsApp.userGroup.home.createOrEditLabel">Create or edit a UserGroup</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : userGroup} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="userOpenidLabel" for="userOpenid">
                    <Translate contentKey="runningcoinCsApp.userGroup.userOpenid">User Openid</Translate>
                  </Label>
                  <AvField type="text" name="userOpenid" />
                </AvGroup>
                <AvGroup>
                  <Label id="groupIdLabel" for="groupId">
                    <Translate contentKey="runningcoinCsApp.userGroup.groupId">Group Id</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="groupId" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/user-group" replace color="info">
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
  userGroup: storeState.userGroup.entity,
  loading: storeState.userGroup.loading,
  updating: storeState.userGroup.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

export default connect(mapStateToProps, mapDispatchToProps)(UserGroupUpdate);
