import * as React from 'react';
import { connect } from 'react-redux';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { IRunningRecord } from 'app/shared/model/running-record.model';
import { getEntity, deleteEntity } from './running-record.reducer';

export interface IRunningRecordDeleteDialogProps {
  getEntity: ICrudGetAction<IRunningRecord>;
  deleteEntity: ICrudDeleteAction<IRunningRecord>;
  runningRecord: IRunningRecord;
  match: any;
  history: any;
}

export class RunningRecordDeleteDialog extends React.Component<IRunningRecordDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.runningRecord.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { runningRecord } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody>
          <Translate contentKey="runningcoinCsApp.runningRecord.delete.question" interpolate={{ id: runningRecord.id }}>
            Are you sure you want to delete this RunningRecord?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ runningRecord }) => ({
  runningRecord: runningRecord.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

export default connect(mapStateToProps, mapDispatchToProps)(RunningRecordDeleteDialog);
