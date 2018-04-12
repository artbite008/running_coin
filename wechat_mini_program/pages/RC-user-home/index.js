//index.js
//获取应用实例
const app = getApp()

const _progressBarColorSets = {
  error: '#ff5151',
  success: '#71c63e',
  info: "#006efc",
  warn: "#ffd80d"
}

const _users = [
  {
    target: "10",
    current: "5",
    thumbUp: 8,
    thumbDown: 8,
    color: 'warning'
  },
  {
    target: "10",
    current: "3",
    thumbUp: 8,
    thumbDown: 8,
    color: 'error'
  },
  {
    target: "10",
    current: "8",
    thumbUp: 8,
    thumbDown: 8,
    color: 'info'
  },
  {
    target: "13",
    current: "5",
    thumbUp: 8,
    thumbDown: 8,
    color: 'warning'
  },
  {
    target: "10",
    current: "3",
    thumbUp: 8,
    thumbDown: 8,
    color: 'warning'
  },
  {
    target: "10",
    current: "10",
    thumbUp: 8,
    thumbDown: 8,
    color: 'success'
  },
  {
    target: "10",
    current: "9",
    thumbUp: 8,
    thumbDown: 8,
    color: 'success'
  }
  ,
  {
    target: "10",
    current: "7",
    thumbUp: 8,
    thumbDown: 8,
    color: 'primary'
  }
];

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    users: _users,
    progressBarColorSets: _progressBarColorSets
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
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
          app.globalData.userInfo = res.userInfo
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
  }
})
