import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IGroupTable } from 'app/shared/model/group-table.model';

export const ACTION_TYPES = {
  FETCH_GROUPTABLE_LIST: 'groupTable/FETCH_GROUPTABLE_LIST',
  FETCH_GROUPTABLE: 'groupTable/FETCH_GROUPTABLE',
  CREATE_GROUPTABLE: 'groupTable/CREATE_GROUPTABLE',
  UPDATE_GROUPTABLE: 'groupTable/UPDATE_GROUPTABLE',
  DELETE_GROUPTABLE: 'groupTable/DELETE_GROUPTABLE',
  RESET: 'groupTable/RESET'
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
    case REQUEST(ACTION_TYPES.FETCH_GROUPTABLE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_GROUPTABLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_GROUPTABLE):
    case REQUEST(ACTION_TYPES.UPDATE_GROUPTABLE):
    case REQUEST(ACTION_TYPES.DELETE_GROUPTABLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_GROUPTABLE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_GROUPTABLE):
    case FAILURE(ACTION_TYPES.CREATE_GROUPTABLE):
    case FAILURE(ACTION_TYPES.UPDATE_GROUPTABLE):
    case FAILURE(ACTION_TYPES.DELETE_GROUPTABLE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_GROUPTABLE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_GROUPTABLE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_GROUPTABLE):
    case SUCCESS(ACTION_TYPES.UPDATE_GROUPTABLE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_GROUPTABLE):
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

const apiUrl = SERVER_API_URL + '/api/group-tables';

// Actions

export const getEntities: ICrudGetAllAction<IGroupTable> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_GROUPTABLE_LIST,
    payload: axios.get(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`) as Promise<IGroupTable>
  };
};

export const getEntity: ICrudGetAction<IGroupTable> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_GROUPTABLE,
    payload: axios.get(requestUrl) as Promise<IGroupTable>
  };
};

export const createEntity: ICrudPutAction<IGroupTable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_GROUPTABLE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IGroupTable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_GROUPTABLE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IGroupTable> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_GROUPTABLE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
