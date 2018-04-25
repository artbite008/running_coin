import { RecordService } from '../service/index';

const iconPlaceholder = '../imgs/avatar-placeholder.jpg';
const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    weekRecords: [],
    range: '',
    iconPh: iconPlaceholder
  },

  onLoad: function (options) {
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
    this.loadData();
  },
  loadData: function() {
    let that = this;
    RecordService
      .getInstance()
      .getEveryoneWeeklyRecord(1)
      .then(res => {
        that.setData({
          weekRecords: [{
            id: '111111111',
            record: res.data.data.allWeeklyRecords
          }],
          range: res.data.data.timeRange
        });
        return new Promise((resolve) => resolve());
      });
  },
  onReady: function () {

  },
  onShow: function () {

  },
  onHide: function () {

  },
  onUnload: function () {

  },
  onPullDownRefresh: function () {

  },
  onReachBottom: function () {

  },
  onShareAppMessage: function () {

  },
  getUserInfo: function (e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  onPullDownRefresh: function () {
    this
      .loadData()
      .then(() => wx.stopPullDownRefresh());
  },
})