import {users as mockUsers} from '../mock/RC-user.mock';
import {progressBarColorSets as _progressBarColorSets} from '../mock/RC-progressBar.mock';
import {achievements} from '../mock/RC-user.mock';

const app = getApp();

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    users: mockUsers,
    progressBarColorSets: _progressBarColorSets,
    showBackdrop: 'none',
    dialogEvent: '',
    markAchievement: 0,
    achievements: achievements
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  goToMyProfile: function() {
    wx.navigateTo({
      url: '../RC-user-profile/index'
    })
  },
  goToReport: function() {
    wx.navigateTo({
      url: '../RC-user-report/index'
    })
  },
  goToVote: function(e) {
    this.setData({
      dialogEvent: 'vote'
    });
    this.backdropMgt();
  },
  mark: function() {
    this.setData({
      dialogEvent: 'mark'
    });
    this.backdropMgt();
  },
  backdropMgt: function() {
    let _showBackdrop = this.data.showBackdrop;
    if (_showBackdrop == 'none') {
      this.setData({
        showBackdrop: 'block'
      });
    } else {
      this.setData({
        showBackdrop: 'none'
      });
    }
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo;
          console.debug(res.userInfo);
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    console.log(e)
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
    })
  }
})
