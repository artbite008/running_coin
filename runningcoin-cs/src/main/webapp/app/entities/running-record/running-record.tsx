import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntities } from './running-record.reducer';
import { IRunningRecord } from 'app/shared/model/running-record.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IRunningRecordProps {
  getEntities: ICrudGetAllAction<IRunningRecord>;
  runningRecordList: IRunningRecord[];
  totalItems: 0;
  location: any;
  history: any;
  match: any;
}

export type IRunningRecordState = IPaginationBaseState;

export class RunningRecord extends React.Component<IRunningRecordProps, IRunningRecordState> {
  state: IRunningRecordState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { runningRecordList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="page-heading">
          <Translate contentKey="runningcoinCsApp.runningRecord.home.title">Running Records</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="runningcoinCsApp.runningRecord.home.createLabel">Create new Running Record</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('userGroupId')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.userGroupId">User Group Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('distance')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.distance">Distance</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('creationTime')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.creationTime">Creation Time</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('lastVotedTime')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.lastVotedTime">Last Voted Time</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('statusField')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.statusField">Status Field</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('score')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.score">Score</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('settledTime')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.settledTime">Settled Time</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('earnedCoins')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.earnedCoins">Earned Coins</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('comments')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.comments">Comments</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('evidence')}>
                  <Translate contentKey="runningcoinCsApp.runningRecord.evidence">Evidence</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {runningRecordList.map((runningRecord, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${runningRecord.id}`} color="link" size="sm">
                      {runningRecord.id}
                    </Button>
                  </td>
                  <td>{runningRecord.userGroupId}</td>
                  <td>{runningRecord.distance}</td>
                  <td>
                    <TextFormat type="date" value={runningRecord.creationTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={runningRecord.lastVotedTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{runningRecord.statusField}</td>
                  <td>{runningRecord.score}</td>
                  <td>
                    <TextFormat type="date" value={runningRecord.settledTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{runningRecord.earnedCoins}</td>
                  <td>{runningRecord.comments}</td>
                  <td>{runningRecord.evidence}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${runningRecord.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${runningRecord.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${runningRecord.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ runningRecord }) => ({
  runningRecordList: runningRecord.entities,
  totalItems: runningRecord.totalItems
});

const mapDispatchToProps = {
  getEntities
};

export default connect(mapStateToProps, mapDispatchToProps)(RunningRecord);
