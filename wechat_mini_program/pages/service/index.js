import {HttpClient as hc} from '../../utils/httpClient';

const envLocal = 'https://test.com';  // mapping in hosts file
//const envQa = 'https://guxiangfly.cn';
//const envQa = 'https://localhost';
//const envQa = 'https://guxiangfly.cn/dev';
//const envQa = 'http://guxiangfly.nat300.top';
const envQa = 'https://www.runningcoin.club';
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
      this.us = new UserService(hc.getInstance().urlPrefix(envQa));
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
    return this.$http.post('/front/user/join', null, user);
  }

  voteUser(voteUserId, groupId, voteUserGroupId, status, runningRecordId) {
    return this.$http.post('/vote', null, {
      voteUserId,
      groupId,
      voteUserGroupId,
      status,
      runningRecordId
    });
  }

  getVoteStatus(runningRecordId, voteUserGroupId) {
    return this.$http.get('/vote', null, {
      runningRecordId, 
      voteUserGroupId
    });
  }

  submitTarget(userId, userGroupId, targetDistance, groupId) {
    return this.$http.post('/submit/sport/target', null, {
      userId,
      userGroupId,
      targetDistance,
      groupId
    })
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
      this.rs = new RecordService(hc.getInstance().urlPrefix(envQa));
      return this.rs;
    }
  }

  $http;

  constructor(httpClient) {
    this.$http = httpClient;
  }

  serverUserLogin(jsCode,olduserId,sessionOpenId) {
    return this.$http.get('/login/onLogin', null, {
        code: jsCode,
        olduserId: olduserId,
        sessionOpenId: sessionOpenId
    });
  }

    serverUserLoginV2(jsCode,userName,icon) {
        return this.$http.post('/login/onLoginV2', null, {
            jsCode: jsCode,
            userName: userName,
            icon: icon,
            groupId: 1
        });
    }

  getUserRecord(userId) {
    return this.$http.get('/record', null, {
      userId: userId
    });
  }

  submitRecord(userOpenId, distance, groupId, evidence) {
    return this.$http.post('/submit/sport/record', null, {
      userOpenId,
      distance,
      groupId,
      evidence
    })
  }

  getEveryoneWeeklyRecord(groupId) {
    return this.$http.post('/front/everyone/weekly/report', null, {
      groupId
    });
  }

  getWeeklyRecordByUserId(userOpenId, groupId) {
    return this.$http.post('/front/user/weekly/report', null, {
      userOpenId,
      groupId
    });
  }
}

/**
 * wrapper service for wx api
 * mainly for promisify
 * - user info service // withCredentials: boolean
 * - login service
 * - check Session Service
 */
class WxService {
  
  static userInfo(withCredentials) {
    return new Promise((resolve, reject) => {
      wx.getUserInfo({
        withCredentials: withCredentials || false,
        success: res => resolve(res),
        fail: reject
      })
    });
  }
  static login() {
    return new Promise((resolve, reject) => {
      wx.login({
        success: res => resolve(res),
        fail: reject
      })
    });
  }
  static checkSession() {
    return new Promise((resolve, reject) => {
      wx.checkSession({
        success: () => resolve(true),
        fail: reject // should trigger login again
      })
    });
  }
}

/**
 * intergation service for wechat api
 * - get User credential info // withCredentials true/false
 */
class WxAuthService {

  static getInstance() {
    if (this.wxs) {
      return this.wxs;
    } else {
      this.wxs = new WxAuthService(hc.getInstance().urlPrefix(envQa));
      return this.wxs;
    }
  }

  $http;
  constructor(httpClient) {
    this.$http = httpClient;
  }

  // decryptData(data) {
  //   return this.$http.post('/front/user/decryptData', data, null);
  // }

  // note, can not get unionId right now so we give up this way
  // note, can not get a login user's unique id or something like this
  // make us really hard to reconnect with the user which clean cache
  // static getUserInfo(withCredentials) {
  //   WxService
  //   .checkSession()
  //     .then(() => WxService.login(),
  //         () => WxService.login())
  //   .then(res => WxService
  //       .userInfo(true)
  //       .then(userInfo => WxAuthService
  //                           .getInstance()
  //                           .decryptData({
  //                             code: res.code || '',
  //                             sign: userInfo.signature,
  //                             iv: userInfo.iv,
  //                             encryptedData: userInfo.encryptedData
  //                           })))
  //   .catch(e => {
  //     console.error(e);
  //     wx.showModal({
  //       title: '提示',
  //       content: `${JSON.stringify(e)}`,
  //       success: function (res) {
  //         if (res.confirm) {
  //         } else if (res.cancel) {
  //         }
  //       }
  //     })
  //   })
  // }
}

export { UserService, RecordService, WxService };