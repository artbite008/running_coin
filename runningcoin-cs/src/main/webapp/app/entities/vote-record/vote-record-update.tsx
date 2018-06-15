import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './vote-record.reducer';
import { IVoteRecord } from 'app/shared/model/vote-record.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IVoteRecordUpdateProps {
  getEntity: ICrudGetAction<IVoteRecord>;
  updateEntity: ICrudPutAction<IVoteRecord>;
  createEntity: ICrudPutAction<IVoteRecord>;
  voteRecord: IVoteRecord;
  reset: Function;
  loading: boolean;
  updating: boolean;
  match: any;
  history: any;
}

export interface IVoteRecordUpdateState {
  isNew: boolean;
}

export class VoteRecordUpdate extends React.Component<IVoteRecordUpdateProps, IVoteRecordUpdateState> {
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
      const { voteRecord } = this.props;
      const entity = {
        ...voteRecord,
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
    this.props.history.push('/entity/vote-record');
  };

  render() {
    const isInvalid = false;
    const { voteRecord, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhi-vote-record-heading">
              <Translate contentKey="runningcoinCsApp.voteRecord.home.createOrEditLabel">Create or edit a VoteRecord</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : voteRecord} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="voteUserGroupIdLabel" for="voteUserGroupId">
                    <Translate contentKey="runningcoinCsApp.voteRecord.voteUserGroupId">Vote User Group Id</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="voteUserGroupId" />
                </AvGroup>
                <AvGroup>
                  <Label id="runingRecordIdLabel" for="runingRecordId">
                    <Translate contentKey="runningcoinCsApp.voteRecord.runingRecordId">Runing Record Id</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="runingRecordId" />
                </AvGroup>
                <AvGroup>
                  <Label id="votedTimeLabel" for="votedTime">
                    <Translate contentKey="runningcoinCsApp.voteRecord.votedTime">Voted Time</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="votedTime" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusFieldLabel" for="statusField">
                    <Translate contentKey="runningcoinCsApp.voteRecord.statusField">Status Field</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="statusField" />
                </AvGroup>
                <AvGroup>
                  <Label id="scoreLabel" for="score">
                    <Translate contentKey="runningcoinCsApp.voteRecord.score">Score</Translate>
                  </Label>
                  <AvField type="number" className="form-control" name="score" />
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    <Translate contentKey="runningcoinCsApp.voteRecord.comments">Comments</Translate>
                  </Label>
                  <AvField type="text" name="comments" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/vote-record" replace color="info">
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
  voteRecord: storeState.voteRecord.entity,
  loading: storeState.voteRecord.loading,
  updating: storeState.voteRecord.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

export default connect(mapStateToProps, mapDispatchToProps)(VoteRecordUpdate);
