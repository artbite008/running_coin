import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IUserGroup } from 'app/shared/model/user-group.model';

export const ACTION_TYPES = {
  FETCH_USERGROUP_LIST: 'userGroup/FETCH_USERGROUP_LIST',
  FETCH_USERGROUP: 'userGroup/FETCH_USERGROUP',
  CREATE_USERGROUP: 'userGroup/CREATE_USERGROUP',
  UPDATE_USERGROUP: 'userGroup/UPDATE_USERGROUP',
  DELETE_USERGROUP: 'userGroup/DELETE_USERGROUP',
  RESET: 'userGroup/RESET'
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
    case REQUEST(ACTION_TYPES.FETCH_USERGROUP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERGROUP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERGROUP):
    case REQUEST(ACTION_TYPES.UPDATE_USERGROUP):
    case REQUEST(ACTION_TYPES.DELETE_USERGROUP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERGROUP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERGROUP):
    case FAILURE(ACTION_TYPES.CREATE_USERGROUP):
    case FAILURE(ACTION_TYPES.UPDATE_USERGROUP):
    case FAILURE(ACTION_TYPES.DELETE_USERGROUP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERGROUP_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERGROUP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERGROUP):
    case SUCCESS(ACTION_TYPES.UPDATE_USERGROUP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERGROUP):
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

const apiUrl = SERVER_API_URL + '/api/user-groups';

// Actions

export const getEntities: ICrudGetAllAction<IUserGroup> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERGROUP_LIST,
    payload: axios.get(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`) as Promise<IUserGroup>
  };
};

export const getEntity: ICrudGetAction<IUserGroup> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERGROUP,
    payload: axios.get(requestUrl) as Promise<IUserGroup>
  };
};

export const createEntity: ICrudPutAction<IUserGroup> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERGROUP,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserGroup> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERGROUP,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserGroup> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERGROUP,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
