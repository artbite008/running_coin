import * as React from 'react';
import { connect } from 'react-redux';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { ITargetDistance } from 'app/shared/model/target-distance.model';
import { getEntity, deleteEntity } from './target-distance.reducer';

export interface ITargetDistanceDeleteDialogProps {
  getEntity: ICrudGetAction<ITargetDistance>;
  deleteEntity: ICrudDeleteAction<ITargetDistance>;
  targetDistance: ITargetDistance;
  match: any;
  history: any;
}

export class TargetDistanceDeleteDialog extends React.Component<ITargetDistanceDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.targetDistance.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { targetDistance } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody>
          <Translate contentKey="runningcoinCsApp.targetDistance.delete.question" interpolate={{ id: targetDistance.id }}>
            Are you sure you want to delete this TargetDistance?
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

const mapStateToProps = ({ targetDistance }) => ({
  targetDistance: targetDistance.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

export default connect(mapStateToProps, mapDispatchToProps)(TargetDistanceDeleteDialog);
