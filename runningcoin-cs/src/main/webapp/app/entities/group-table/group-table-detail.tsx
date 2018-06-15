import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity } from './group-table.reducer';
import { IGroupTable } from 'app/shared/model/group-table.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGroupTableDetailProps {
  getEntity: ICrudGetAction<IGroupTable>;
  groupTable: IGroupTable;
  match: any;
}

export class GroupTableDetail extends React.Component<IGroupTableDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { groupTable } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="runningcoinCsApp.groupTable.detail.title">GroupTable</Translate> [<b>{groupTable.id}</b>]
          </h2>
          <Row size="md">
            <dl className="jh-entity-details">
              <dt>
                <span id="groupName">
                  <Translate contentKey="runningcoinCsApp.groupTable.groupName">Group Name</Translate>
                </span>
              </dt>
              <dd>{groupTable.groupName}</dd>
              <dt>
                <span id="metaData">
                  <Translate contentKey="runningcoinCsApp.groupTable.metaData">Meta Data</Translate>
                </span>
              </dt>
              <dd>{groupTable.metaData}</dd>
            </dl>
          </Row>
          <Button tag={Link} to="/entity/group-table" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          <Button tag={Link} to={`/entity/group-table/${groupTable.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ groupTable }) => ({
  groupTable: groupTable.entity
});

const mapDispatchToProps = { getEntity };

export default connect(mapStateToProps, mapDispatchToProps)(GroupTableDetail);
