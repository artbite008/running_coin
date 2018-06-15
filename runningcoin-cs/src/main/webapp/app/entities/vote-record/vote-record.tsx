import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntities } from './vote-record.reducer';
import { IVoteRecord } from 'app/shared/model/vote-record.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IVoteRecordProps {
  getEntities: ICrudGetAllAction<IVoteRecord>;
  voteRecordList: IVoteRecord[];
  totalItems: 0;
  location: any;
  history: any;
  match: any;
}

export type IVoteRecordState = IPaginationBaseState;

export class VoteRecord extends React.Component<IVoteRecordProps, IVoteRecordState> {
  state: IVoteRecordState = {
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
    const { voteRecordList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="page-heading">
          <Translate contentKey="runningcoinCsApp.voteRecord.home.title">Vote Records</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="runningcoinCsApp.voteRecord.home.createLabel">Create new Vote Record</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('voteUserGroupId')}>
                  <Translate contentKey="runningcoinCsApp.voteRecord.voteUserGroupId">Vote User Group Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('runingRecordId')}>
                  <Translate contentKey="runningcoinCsApp.voteRecord.runingRecordId">Runing Record Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('votedTime')}>
                  <Translate contentKey="runningcoinCsApp.voteRecord.votedTime">Voted Time</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('statusField')}>
                  <Translate contentKey="runningcoinCsApp.voteRecord.statusField">Status Field</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('score')}>
                  <Translate contentKey="runningcoinCsApp.voteRecord.score">Score</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('comments')}>
                  <Translate contentKey="runningcoinCsApp.voteRecord.comments">Comments</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {voteRecordList.map((voteRecord, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${voteRecord.id}`} color="link" size="sm">
                      {voteRecord.id}
                    </Button>
                  </td>
                  <td>{voteRecord.voteUserGroupId}</td>
                  <td>{voteRecord.runingRecordId}</td>
                  <td>{voteRecord.votedTime}</td>
                  <td>{voteRecord.statusField}</td>
                  <td>{voteRecord.score}</td>
                  <td>{voteRecord.comments}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${voteRecord.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${voteRecord.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${voteRecord.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ voteRecord }) => ({
  voteRecordList: voteRecord.entities,
  totalItems: voteRecord.totalItems
});

const mapDispatchToProps = {
  getEntities
};

export default connect(mapStateToProps, mapDispatchToProps)(VoteRecord);
