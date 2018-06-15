import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity } from './running-record.reducer';
import { IRunningRecord } from 'app/shared/model/running-record.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRunningRecordDetailProps {
  getEntity: ICrudGetAction<IRunningRecord>;
  runningRecord: IRunningRecord;
  match: any;
}

export class RunningRecordDetail extends React.Component<IRunningRecordDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { runningRecord } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="runningcoinCsApp.runningRecord.detail.title">RunningRecord</Translate> [<b>{runningRecord.id}</b>]
          </h2>
          <Row size="md">
            <dl className="jh-entity-details">
              <dt>
                <span id="userGroupId">
                  <Translate contentKey="runningcoinCsApp.runningRecord.userGroupId">User Group Id</Translate>
                </span>
              </dt>
              <dd>{runningRecord.userGroupId}</dd>
              <dt>
                <span id="distance">
                  <Translate contentKey="runningcoinCsApp.runningRecord.distance">Distance</Translate>
                </span>
              </dt>
              <dd>{runningRecord.distance}</dd>
              <dt>
                <span id="creationTime">
                  <Translate contentKey="runningcoinCsApp.runningRecord.creationTime">Creation Time</Translate>
                </span>
              </dt>
              <dd>
                <TextFormat value={runningRecord.creationTime} type="date" format={APP_DATE_FORMAT} />
              </dd>
              <dt>
                <span id="lastVotedTime">
                  <Translate contentKey="runningcoinCsApp.runningRecord.lastVotedTime">Last Voted Time</Translate>
                </span>
              </dt>
              <dd>
                <TextFormat value={runningRecord.lastVotedTime} type="date" format={APP_DATE_FORMAT} />
              </dd>
              <dt>
                <span id="statusField">
                  <Translate contentKey="runningcoinCsApp.runningRecord.statusField">Status Field</Translate>
                </span>
              </dt>
              <dd>{runningRecord.statusField}</dd>
              <dt>
                <span id="score">
                  <Translate contentKey="runningcoinCsApp.runningRecord.score">Score</Translate>
                </span>
              </dt>
              <dd>{runningRecord.score}</dd>
              <dt>
                <span id="settledTime">
                  <Translate contentKey="runningcoinCsApp.runningRecord.settledTime">Settled Time</Translate>
                </span>
              </dt>
              <dd>
                <TextFormat value={runningRecord.settledTime} type="date" format={APP_DATE_FORMAT} />
              </dd>
              <dt>
                <span id="earnedCoins">
                  <Translate contentKey="runningcoinCsApp.runningRecord.earnedCoins">Earned Coins</Translate>
                </span>
              </dt>
              <dd>{runningRecord.earnedCoins}</dd>
              <dt>
                <span id="comments">
                  <Translate contentKey="runningcoinCsApp.runningRecord.comments">Comments</Translate>
                </span>
              </dt>
              <dd>{runningRecord.comments}</dd>
              <dt>
                <span id="evidence">
                  <Translate contentKey="runningcoinCsApp.runningRecord.evidence">Evidence</Translate>
                </span>
              </dt>
              <dd>{runningRecord.evidence}</dd>
            </dl>
          </Row>
          <Button tag={Link} to="/entity/running-record" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          <Button tag={Link} to={`/entity/running-record/${runningRecord.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ runningRecord }) => ({
  runningRecord: runningRecord.entity
});

const mapDispatchToProps = { getEntity };

export default connect(mapStateToProps, mapDispatchToProps)(RunningRecordDetail);
