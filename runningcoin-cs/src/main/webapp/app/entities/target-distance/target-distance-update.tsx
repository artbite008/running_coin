import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './target-distance.reducer';
import { ITargetDistance } from 'app/shared/model/target-distance.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ITargetDistanceUpdateProps {
  getEntity: ICrudGetAction<ITargetDistance>;
  updateEntity: ICrudPutAction<ITargetDistance>;
  createEntity: ICrudPutAction<ITargetDistance>;
  targetDistance: ITargetDistance;
  reset: Function;
  loading: boolean;
  updating: boolean;
  match: any;
  history: any;
}

export interface ITargetDistanceUpdateState {
  isNew: boolean;
}

export class TargetDistanceUpdate extends React.Component<ITargetDistanceUpdateProps, ITargetDistanceUpdateState> {
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
    values.creationTime = new Date(values.creationTime);

    if (errors.length === 0) {
      const { targetDistance } = this.props;
      const entity = {
        ...targetDistance,
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
    this.props.history.push('/entity/target-distance');
  };

  render() {
    const isInvalid = false;
    const { targetDistance, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhi-target-distance-heading">
              <Translate contentKey="runningcoinCsApp.targetDistance.home.createOrEditLabel">Create or edit a TargetDistance</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : targetDistance} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="userGroupIdLabel" for="userGroupId">
                    <Translate contentKey="runningcoinCsApp.targetDistance.userGroupId">User Group Id</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="userGroupId" />
                </AvGroup>
                <AvGroup>
                  <Label id="creationTimeLabel" for="creationTime">
                    <Translate contentKey="runningcoinCsApp.targetDistance.creationTime">Creation Time</Translate>
                  </Label>
                  <AvInput
                    type="datetime-local"
                    className="form-control"
                    name="creationTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.targetDistance.creationTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="targetDistanceLabel" for="targetDistance">
                    <Translate contentKey="runningcoinCsApp.targetDistance.targetDistance">Target Distance</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="targetDistance" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/target-distance" replace color="info">
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
  targetDistance: storeState.targetDistance.entity,
  loading: storeState.targetDistance.loading,
  updating: storeState.targetDistance.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

export default connect(mapStateToProps, mapDispatchToProps)(TargetDistanceUpdate);
