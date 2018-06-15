import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IRunningRecord } from 'app/shared/model/running-record.model';

export const ACTION_TYPES = {
  FETCH_RUNNINGRECORD_LIST: 'runningRecord/FETCH_RUNNINGRECORD_LIST',
  FETCH_RUNNINGRECORD: 'runningRecord/FETCH_RUNNINGRECORD',
  CREATE_RUNNINGRECORD: 'runningRecord/CREATE_RUNNINGRECORD',
  UPDATE_RUNNINGRECORD: 'runningRecord/UPDATE_RUNNINGRECORD',
  DELETE_RUNNINGRECORD: 'runningRecord/DELETE_RUNNINGRECORD',
  RESET: 'runningRecord/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: {},
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

// Reducer

export default (state = initialState, action) => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RUNNINGRECORD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RUNNINGRECORD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_RUNNINGRECORD):
    case REQUEST(ACTION_TYPES.UPDATE_RUNNINGRECORD):
    case REQUEST(ACTION_TYPES.DELETE_RUNNINGRECORD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_RUNNINGRECORD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RUNNINGRECORD):
    case FAILURE(ACTION_TYPES.CREATE_RUNNINGRECORD):
    case FAILURE(ACTION_TYPES.UPDATE_RUNNINGRECORD):
    case FAILURE(ACTION_TYPES.DELETE_RUNNINGRECORD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_RUNNINGRECORD_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_RUNNINGRECORD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_RUNNINGRECORD):
    case SUCCESS(ACTION_TYPES.UPDATE_RUNNINGRECORD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_RUNNINGRECORD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = SERVER_API_URL + '/api/running-records';

// Actions

export const getEntities: ICrudGetAllAction<IRunningRecord> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_RUNNINGRECORD_LIST,
    payload: axios.get(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`) as Promise<IRunningRecord>
  };
};

export const getEntity: ICrudGetAction<IRunningRecord> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RUNNINGRECORD,
    payload: axios.get(requestUrl) as Promise<IRunningRecord>
  };
};

export const createEntity: ICrudPutAction<IRunningRecord> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RUNNINGRECORD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRunningRecord> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RUNNINGRECORD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRunningRecord> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RUNNINGRECORD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
