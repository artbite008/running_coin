import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity } from './target-distance.reducer';
import { ITargetDistance } from 'app/shared/model/target-distance.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITargetDistanceDetailProps {
  getEntity: ICrudGetAction<ITargetDistance>;
  targetDistance: ITargetDistance;
  match: any;
}

export class TargetDistanceDetail extends React.Component<ITargetDistanceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { targetDistance } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="runningcoinCsApp.targetDistance.detail.title">TargetDistance</Translate> [<b>{targetDistance.id}</b>]
          </h2>
          <Row size="md">
            <dl className="jh-entity-details">
              <dt>
                <span id="userGroupId">
                  <Translate contentKey="runningcoinCsApp.targetDistance.userGroupId">User Group Id</Translate>
                </span>
              </dt>
              <dd>{targetDistance.userGroupId}</dd>
              <dt>
                <span id="creationTime">
                  <Translate contentKey="runningcoinCsApp.targetDistance.creationTime">Creation Time</Translate>
                </span>
              </dt>
              <dd>
                <TextFormat value={targetDistance.creationTime} type="date" format={APP_DATE_FORMAT} />
              </dd>
              <dt>
                <span id="targetDistance">
                  <Translate contentKey="runningcoinCsApp.targetDistance.targetDistance">Target Distance</Translate>
                </span>
              </dt>
              <dd>{targetDistance.targetDistance}</dd>
            </dl>
          </Row>
          <Button tag={Link} to="/entity/target-distance" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          <Button tag={Link} to={`/entity/target-distance/${targetDistance.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ targetDistance }) => ({
  targetDistance: targetDistance.entity
});

const mapDispatchToProps = { getEntity };

export default connect(mapStateToProps, mapDispatchToProps)(TargetDistanceDetail);
