import { progressBarColorSets as _progressBarColorSets} from '../mock/RC-progressBar.mock.js';
import { myWeeklyRecords } from '../mock/RC-user.mock';
import { plans } from '../mock/RC-user.mock';

const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    progressBarColorSets: _progressBarColorSets,
    weeklyReords: myWeeklyRecords,
    showBackdrop: 'none',
    dialogEvent: '',
    plan: 5,
    plans: plans
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
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

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  backdropMgt: function () {
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
  goToPlan: function (e) {
    this.setData({
      dialogEvent: 'plan'
    });
    this.backdropMgt();
  },
  bindChange: function (e) {
    const val = e.detail.value
    this.setData({
      plan: plans[val[0]]
    })
  }
})