import {HttpClient as hc} from '../../utils/httpClient';

const envLocal = 'https://test.com';  // mapping in hosts file
const envQa = 'http://127.0.0.1';
const envProd = 'http://127.0.0.1';

/**
 * user services for
 * query user info by user Id
 * create user info
 * vote user
 */
class UserService {

  static getInstance() {
    if (this.us) {
      return this.us;
    } else {
      this.us = new UserService(hc.getInstance().urlPrefix(envLocal));
      return this.us;
    }
  }
  
  $http;

  constructor(httpClient) {
    this.$http = httpClient;
  }

  getUserById(userId) {
    return this.$http.get(`/user/${userId}`, null, null);
  }

  getAllUser() {
    return this.$http.get('/list/users', null, null);
  }
  
  createAndUpdateUser(user) {
    return this.$http.post('/user', null, user);
  }

  voteUser(userId, vote, cancel) {
    return this.$http.post('/vote', null, {
      userId: userId,
      voteStatus: vote,
      cancel: cancel
    });
  }
}

/**
 * record services for
 * query record by userId
 * query weekly record
 * query all record
 */
class RecordService {
    
  static getInstance() {
    if (this.rs) {
      return this.rs;
    } else {
      this.rs = new RecordService(hc.getInstance());
      return this.rs;
    }
  }

  $http;

  constructor(httpClient) {
    this.$http = httpClient;
  }

  getUserRecord(userId) {
    return this.$http.get('/record', null, {
      userId: userId
    });
  }

  getWeeklyRecord() {
    return this.$http.get('/record/week', null, null);
  }

  getWeeklyRecordByUserId(userId) {
    return this.$http.get('/record/week', null, {
      userId: userId
    });
  }
}

export { UserService, RecordService };