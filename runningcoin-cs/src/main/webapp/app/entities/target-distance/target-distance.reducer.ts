import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { ITargetDistance } from 'app/shared/model/target-distance.model';

export const ACTION_TYPES = {
  FETCH_TARGETDISTANCE_LIST: 'targetDistance/FETCH_TARGETDISTANCE_LIST',
  FETCH_TARGETDISTANCE: 'targetDistance/FETCH_TARGETDISTANCE',
  CREATE_TARGETDISTANCE: 'targetDistance/CREATE_TARGETDISTANCE',
  UPDATE_TARGETDISTANCE: 'targetDistance/UPDATE_TARGETDISTANCE',
  DELETE_TARGETDISTANCE: 'targetDistance/DELETE_TARGETDISTANCE',
  RESET: 'targetDistance/RESET'
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
    case REQUEST(ACTION_TYPES.FETCH_TARGETDISTANCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TARGETDISTANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TARGETDISTANCE):
    case REQUEST(ACTION_TYPES.UPDATE_TARGETDISTANCE):
    case REQUEST(ACTION_TYPES.DELETE_TARGETDISTANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TARGETDISTANCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TARGETDISTANCE):
    case FAILURE(ACTION_TYPES.CREATE_TARGETDISTANCE):
    case FAILURE(ACTION_TYPES.UPDATE_TARGETDISTANCE):
    case FAILURE(ACTION_TYPES.DELETE_TARGETDISTANCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TARGETDISTANCE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TARGETDISTANCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TARGETDISTANCE):
    case SUCCESS(ACTION_TYPES.UPDATE_TARGETDISTANCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TARGETDISTANCE):
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

const apiUrl = SERVER_API_URL + '/api/target-distances';

// Actions

export const getEntities: ICrudGetAllAction<ITargetDistance> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TARGETDISTANCE_LIST,
    payload: axios.get(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`) as Promise<ITargetDistance>
  };
};

export const getEntity: ICrudGetAction<ITargetDistance> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TARGETDISTANCE,
    payload: axios.get(requestUrl) as Promise<ITargetDistance>
  };
};

export const createEntity: ICrudPutAction<ITargetDistance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TARGETDISTANCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITargetDistance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TARGETDISTANCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITargetDistance> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TARGETDISTANCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
