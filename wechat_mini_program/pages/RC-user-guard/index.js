export const registerCodePlaceholder = 'payment2018';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    registerCode: '',
    register: wx.getStorageSync('register')
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (this.data.register === 'true') {
      this.goHome();
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

  /**
   * for using input
   */
  bindKeyInput: function(e) {
    this.setData({
      registerCode: e.detail.value
    });
  },

  verfyRegisterCode: function() {
    let that = this;
    wx.showLoading({
      title: 'verifying...'
    });
    setTimeout(function () {
      wx.hideLoading();
      if (that.data.registerCode == registerCodePlaceholder) {
        wx.showToast({
          title: 'bingo!',
          icon: 'success',
          duration: 2000
        });
        that.goHome();
        wx.setStorageSync("register", "true");
        that.setData({
          register: 'true'
        });
      } else {
        wx.showToast({
          title: 'sorry !',
          icon: 'none',
          duration: 1000
        });
      }
    }, 1000);
    
  },

  goHome: function() {
    wx.redirectTo({
      url: '../RC-user-home/index',
    });
  }
})