import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IVoteRecord } from 'app/shared/model/vote-record.model';

export const ACTION_TYPES = {
  FETCH_VOTERECORD_LIST: 'voteRecord/FETCH_VOTERECORD_LIST',
  FETCH_VOTERECORD: 'voteRecord/FETCH_VOTERECORD',
  CREATE_VOTERECORD: 'voteRecord/CREATE_VOTERECORD',
  UPDATE_VOTERECORD: 'voteRecord/UPDATE_VOTERECORD',
  DELETE_VOTERECORD: 'voteRecord/DELETE_VOTERECORD',
  RESET: 'voteRecord/RESET'
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
    case REQUEST(ACTION_TYPES.FETCH_VOTERECORD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VOTERECORD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VOTERECORD):
    case REQUEST(ACTION_TYPES.UPDATE_VOTERECORD):
    case REQUEST(ACTION_TYPES.DELETE_VOTERECORD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_VOTERECORD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VOTERECORD):
    case FAILURE(ACTION_TYPES.CREATE_VOTERECORD):
    case FAILURE(ACTION_TYPES.UPDATE_VOTERECORD):
    case FAILURE(ACTION_TYPES.DELETE_VOTERECORD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_VOTERECORD_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VOTERECORD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VOTERECORD):
    case SUCCESS(ACTION_TYPES.UPDATE_VOTERECORD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VOTERECORD):
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

const apiUrl = SERVER_API_URL + '/api/vote-records';

// Actions

export const getEntities: ICrudGetAllAction<IVoteRecord> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_VOTERECORD_LIST,
    payload: axios.get(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`) as Promise<IVoteRecord>
  };
};

export const getEntity: ICrudGetAction<IVoteRecord> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VOTERECORD,
    payload: axios.get(requestUrl) as Promise<IVoteRecord>
  };
};

export const createEntity: ICrudPutAction<IVoteRecord> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VOTERECORD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVoteRecord> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VOTERECORD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVoteRecord> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VOTERECORD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
