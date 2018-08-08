import {progressBarColorSets as _progressBarColorSets} from '../mock/RC-progressBar.mock.js';
import {plans} from '../mock/RC-user.mock';
import {UserService, WxService as WX, RecordService} from '../service/index';
import {hashToInt} from '../../utils/util';
import {getTheHomePageModel} from "../../utils/CommonUtils";

const app = getApp();

Page({

    data: {
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        progressBarColorSets: _progressBarColorSets,
        weeklyReords: [],
        showBackdrop: 'none',
        dialogEvent: '',
        plan: plans[0],
        plans: plans
    },

    onShow: function(){
        if (app.globalData.userInfo) {
            this.setData({
                userInfo: app.globalData.userInfo,
                hasUserInfo: true
            })
        }
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
        }
        this.loadData()
            .then(() => this.loadData())
            .then(() => wx.stopPullDownRefresh());
    },
    onReady: function () {

    },
    onShow: function () {

    },
    onHide: function () {

    },
    onUnload: function () {

    },
    onReachBottom: function () {

    },
    onShareAppMessage: function () {

    },
    loadData: function () {
        let that = this;
        return RecordService
            .getInstance()
            .getWeeklyRecordByUserId(wx.getStorageSync('sessionOpenId'), 1)
            .then(res => {
                that.setData({
                    weeklyReords: {
                        range: res.data.data.timeRange,
                        record: res.data.data.userRecords
                    }
                });
                return new Promise((resolve) => resolve());
            });
    },
    refreshUserInfo: function () {
        return new Promise((resolve, reject) => {
            getTheHomePageModel()
                .then(homePageModel => {
                    app.globalData.userInfo =homePageModel.userRecord;
                    this.setData({
                        userInfo: homePageModel.userRecord || {},
                    });
                    resolve("success");
                })
        })
    },


    getUserInfo: function (e) {
        this.setData({
            userInfo: app.globalData.userInfo,
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
    },
    submitTarget: function (e) {
        const plan = this.data.plan;
        const userInfo = this.data.userInfo;
        UserService
            .getInstance()
            .submitTarget(userInfo.userId, userInfo.userGroupId, plan, 1)
            .then(res => {
                if (res.data.code == 500) {
                    wx.showToast({
                        title: res.data.msg,
                        icon: 'none',
                        duration: 1500
                    })
                } else {
                    wx.showToast({title: 'submit Success!'})
                }

                this.backdropMgt();
                return this.refreshUserInfo();
            })
            .then(() => this.loadData())
            .then(() => wx.stopPullDownRefresh());

    },
    onPullDownRefresh: function () {
        this.refreshUserInfo();
        this
            .loadData()
            .then(() => wx.stopPullDownRefresh());
    }
})