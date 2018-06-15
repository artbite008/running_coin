import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity } from './vote-record.reducer';
import { IVoteRecord } from 'app/shared/model/vote-record.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVoteRecordDetailProps {
  getEntity: ICrudGetAction<IVoteRecord>;
  voteRecord: IVoteRecord;
  match: any;
}

export class VoteRecordDetail extends React.Component<IVoteRecordDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { voteRecord } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="runningcoinCsApp.voteRecord.detail.title">VoteRecord</Translate> [<b>{voteRecord.id}</b>]
          </h2>
          <Row size="md">
            <dl className="jh-entity-details">
              <dt>
                <span id="voteUserGroupId">
                  <Translate contentKey="runningcoinCsApp.voteRecord.voteUserGroupId">Vote User Group Id</Translate>
                </span>
              </dt>
              <dd>{voteRecord.voteUserGroupId}</dd>
              <dt>
                <span id="runingRecordId">
                  <Translate contentKey="runningcoinCsApp.voteRecord.runingRecordId">Runing Record Id</Translate>
                </span>
              </dt>
              <dd>{voteRecord.runingRecordId}</dd>
              <dt>
                <span id="votedTime">
                  <Translate contentKey="runningcoinCsApp.voteRecord.votedTime">Voted Time</Translate>
                </span>
              </dt>
              <dd>{voteRecord.votedTime}</dd>
              <dt>
                <span id="statusField">
                  <Translate contentKey="runningcoinCsApp.voteRecord.statusField">Status Field</Translate>
                </span>
              </dt>
              <dd>{voteRecord.statusField}</dd>
              <dt>
                <span id="score">
                  <Translate contentKey="runningcoinCsApp.voteRecord.score">Score</Translate>
                </span>
              </dt>
              <dd>{voteRecord.score}</dd>
              <dt>
                <span id="comments">
                  <Translate contentKey="runningcoinCsApp.voteRecord.comments">Comments</Translate>
                </span>
              </dt>
              <dd>{voteRecord.comments}</dd>
            </dl>
          </Row>
          <Button tag={Link} to="/entity/vote-record" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          <Button tag={Link} to={`/entity/vote-record/${voteRecord.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ voteRecord }) => ({
  voteRecord: voteRecord.entity
});

const mapDispatchToProps = { getEntity };

export default connect(mapStateToProps, mapDispatchToProps)(VoteRecordDetail);
