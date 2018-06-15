import * as React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './group-table.reducer';
import { IGroupTable } from 'app/shared/model/group-table.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IGroupTableUpdateProps {
  getEntity: ICrudGetAction<IGroupTable>;
  updateEntity: ICrudPutAction<IGroupTable>;
  createEntity: ICrudPutAction<IGroupTable>;
  groupTable: IGroupTable;
  reset: Function;
  loading: boolean;
  updating: boolean;
  match: any;
  history: any;
}

export interface IGroupTableUpdateState {
  isNew: boolean;
}

export class GroupTableUpdate extends React.Component<IGroupTableUpdateProps, IGroupTableUpdateState> {
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
      const { groupTable } = this.props;
      const entity = {
        ...groupTable,
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
    this.props.history.push('/entity/group-table');
  };

  render() {
    const isInvalid = false;
    const { groupTable, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhi-group-table-heading">
              <Translate contentKey="runningcoinCsApp.groupTable.home.createOrEditLabel">Create or edit a GroupTable</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : groupTable} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="groupNameLabel" for="groupName">
                    <Translate contentKey="runningcoinCsApp.groupTable.groupName">Group Name</Translate>
                  </Label>
                  <AvField type="text" name="groupName" />
                </AvGroup>
                <AvGroup>
                  <Label id="metaDataLabel" for="metaData">
                    <Translate contentKey="runningcoinCsApp.groupTable.metaData">Meta Data</Translate>
                  </Label>
                  <AvField type="text" name="metaData" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/group-table" replace color="info">
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
  groupTable: storeState.groupTable.entity,
  loading: storeState.groupTable.loading,
  updating: storeState.groupTable.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

export default connect(mapStateToProps, mapDispatchToProps)(GroupTableUpdate);
