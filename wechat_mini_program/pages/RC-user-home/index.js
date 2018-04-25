import { users as mockUsers } from '../mock/RC-user.mock';
import { progressBarColorSets as _progressBarColorSets } from '../mock/RC-progressBar.mock';
import { achievements } from '../mock/RC-user.mock';
import { UserService, WxService as WX, RecordService } from '../service/index';
import { hashToInt } from '../../utils/util';

const app = getApp();

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    users: [],
    progressBarColorSets: _progressBarColorSets,
    showBackdrop: false,
    dialogEvent: '',
    markAchievement: 0,
    achievements: achievements,
    register: wx.getStorageSync('register')
  },
  goToGuard: function () { // do not use navigateTo
    wx.redirectTo({
      url: '../RC-user-guard/index'
    })
  },
  goToMyProfile: function () {
    wx.navigateTo({
      url: '../RC-user-profile/index'
    })
  },
  goToReport: function () {
    wx.navigateTo({
      url: '../RC-user-report/index'
    })
  },
  goToVote: function (e) {
    this.setData({
      dialogEvent: 'vote'
    });
    this.backdropMgt();
  },
  mark: function () {
    this.setData({
      dialogEvent: 'mark'
    });
    this.backdropMgt();
  },
  backdropMgt: function () {
    let _showBackdrop = this.data.showBackdrop;
    this.setData({
      showBackdrop: !_showBackdrop
    });
  },
  onLoad: function () {
    const registerFlagFromStorage = wx.getStorageSync('register');
    if (registerFlagFromStorage !== 'true') {
      this.goToGuard();
      return;
    } else {
      this.setData({
        register: registerFlagFromStorage
      });
    }
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      };
      WX
        .userInfo(false)
        .then(res => {
          const userInfo = res.userInfo;
          app.globalData.userInfo = userInfo;
          this.setData({
            userInfo: userInfo,
            hasUserInfo: true
          });
          UserService
          .getInstance()
            .createAndUpdateUser({
            userName: userInfo.nickName,
            groupId: 1,
            unionId: hashToInt(`${userInfo.nickName}-${userInfo.city}-${userInfo.province}-${userInfo.country}`),
            icon: userInfo.avatarUrl
          })
        });
    } else {
      // this is from wechat offical demo for some kind of backward compatible
      WX
        .userInfo(true)
        .then(res => {
          app.globalData.userInfo = res.userInfo;
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          });
        });
    }
  },
  getUserInfo: function (e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  bindChange: function (e) {
    const val = e.detail.value
    this.setData({
      markAchievement: achievements[val[0]]
    });
  },
  submitRecord: function (e) {
    const record = this.data.markAchievement;
    RecordService
      .getInstance()
      .submitRecord(244868665, record, 1, '')
      .then(res => {
        wx.showToast({
          title: 'submit!'
        });
      });
  }
})
