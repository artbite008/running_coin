import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './running-record.reducer';
import { IRunningRecord } from 'app/shared/model/running-record.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IRunningRecordUpdateProps {
  getEntity: ICrudGetAction<IRunningRecord>;
  updateEntity: ICrudPutAction<IRunningRecord>;
  createEntity: ICrudPutAction<IRunningRecord>;
  runningRecord: IRunningRecord;
  reset: Function;
  loading: boolean;
  updating: boolean;
  match: any;
  history: any;
}

export interface IRunningRecordUpdateState {
  isNew: boolean;
}

export class RunningRecordUpdate extends React.Component<IRunningRecordUpdateProps, IRunningRecordUpdateState> {
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
    values.lastVotedTime = new Date(values.lastVotedTime);
    values.settledTime = new Date(values.settledTime);

    if (errors.length === 0) {
      const { runningRecord } = this.props;
      const entity = {
        ...runningRecord,
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
    this.props.history.push('/entity/running-record');
  };

  render() {
    const isInvalid = false;
    const { runningRecord, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhi-running-record-heading">
              <Translate contentKey="runningcoinCsApp.runningRecord.home.createOrEditLabel">Create or edit a RunningRecord</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : runningRecord} onSubmit={this.saveEntity}>
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
                    <Translate contentKey="runningcoinCsApp.runningRecord.userGroupId">User Group Id</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="userGroupId" />
                </AvGroup>
                <AvGroup>
                  <Label id="distanceLabel" for="distance">
                    <Translate contentKey="runningcoinCsApp.runningRecord.distance">Distance</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="distance" />
                </AvGroup>
                <AvGroup>
                  <Label id="creationTimeLabel" for="creationTime">
                    <Translate contentKey="runningcoinCsApp.runningRecord.creationTime">Creation Time</Translate>
                  </Label>
                  <AvInput
                    type="datetime-local"
                    className="form-control"
                    name="creationTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.runningRecord.creationTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastVotedTimeLabel" for="lastVotedTime">
                    <Translate contentKey="runningcoinCsApp.runningRecord.lastVotedTime">Last Voted Time</Translate>
                  </Label>
                  <AvInput
                    type="datetime-local"
                    className="form-control"
                    name="lastVotedTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.runningRecord.lastVotedTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusFieldLabel" for="statusField">
                    <Translate contentKey="runningcoinCsApp.runningRecord.statusField">Status Field</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="statusField" />
                </AvGroup>
                <AvGroup>
                  <Label id="scoreLabel" for="score">
                    <Translate contentKey="runningcoinCsApp.runningRecord.score">Score</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="score" />
                </AvGroup>
                <AvGroup>
                  <Label id="settledTimeLabel" for="settledTime">
                    <Translate contentKey="runningcoinCsApp.runningRecord.settledTime">Settled Time</Translate>
                  </Label>
                  <AvInput
                    type="datetime-local"
                    className="form-control"
                    name="settledTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.runningRecord.settledTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="earnedCoinsLabel" for="earnedCoins">
                    <Translate contentKey="runningcoinCsApp.runningRecord.earnedCoins">Earned Coins</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="earnedCoins" />
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    <Translate contentKey="runningcoinCsApp.runningRecord.comments">Comments</Translate>
                  </Label>
                  <AvField type="text" name="comments" />
                </AvGroup>
                <AvGroup>
                  <Label id="evidenceLabel" for="evidence">
                    <Translate contentKey="runningcoinCsApp.runningRecord.evidence">Evidence</Translate>
                  </Label>
                  <AvField type="text" name="evidence" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/running-record" replace color="info">
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
  runningRecord: storeState.runningRecord.entity,
  loading: storeState.runningRecord.loading,
  updating: storeState.runningRecord.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

export default connect(mapStateToProps, mapDispatchToProps)(RunningRecordUpdate);
