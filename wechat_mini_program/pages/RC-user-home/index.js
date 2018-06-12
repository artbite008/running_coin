import {progressBarColorSets as _progressBarColorSets} from '../mock/RC-progressBar.mock';
import {achievements} from '../mock/RC-user.mock';
import {UserService, WxService as WX, RecordService} from '../service/index';
import {hashToInt} from '../../utils/util';


const iconPlaceholder = '../imgs/avatar-placeholder.jpg';

const app = getApp();

Page({
    data: {
        iconph: iconPlaceholder,
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        users: [],
        progressBarColorSets: _progressBarColorSets,
        showBackdrop: false,
        dialogEvent: '',
        markAchievement: achievements[0],
        achievements: achievements,
        register: wx.getStorageSync('register'),
        // for vote
        whoIsGonnaBeVote: {},
        liked: false,
        disliked: false
    },
    getUserInfobywx: function () {
        this
            .loadData()
            .then(() => wx.stopPullDownRefresh());
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
        const currentUser = e.currentTarget.dataset.user;
        let that = this;
        UserService
            .getInstance()
            .getVoteStatus(currentUser.runningRecordId, this.data.userInfo.userGroupId)
            .then(res => {
                const voteStatus = res.data.data.voteStatus;
                that.voteStatusChooser(voteStatus);
                that.setData({
                    dialogEvent: 'vote',
                    whoIsGonnaBeVote: currentUser
                });
                that.backdropMgt();
            });
    },
    voteStatusChooser: function (voteStatus) {
        const vs = `${voteStatus}`;
        if (vs === '0') { // liked
            this.setData({
                liked: true,
                disliked: false
            });
        } else if (vs === '2') { // disliked
            this.setData({
                liked: false,
                disliked: true
            });
        } else {  // not yet or unknown
            this.setData({
                liked: false,
                disliked: false
            });
        }
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

        if (JSON.stringify(this.data.userInfo) == "{}") {
            console.log("===userinfo 是空");

        }
        console.log("执行到 home/index.js 101")
        if (app.globalData.userInfo != null) {
            this.setData({
                userInfo: app.globalData.userInfo,
                hasUserInfo: true
            })
        } else {
            /* console.log("执行到 home/index.js 114")
             WX.userInfo(true)
                 .then(res => {
                     app.globalData.userInfo = res.userInfo;
                     this.setData({
                         userInfo: res.userInfo,
                         hasUserInfo: true
                     });
                     if (!wx.getStorageSync('sessionOpenId')) {
                         WX.login().then(res => {
                             console.log("get jsCode to login" + res.code);

                             RecordService.getInstance().serverUserLogin(
                                 res.code,
                                 hashToInt(`${userInfo.nickName}-${userInfo.city}-${userInfo.province}-${userInfo.country}`),
                                 null
                             )
                         }).then(res => {
                             console.dir(res);
                             console.log("store the openid in app" + res.data.data);
                             app.globalData.sessionOpenId = res.data.data;
                             wx.setStorageSync('sessionOpenId', res.data.data);
                         })
                     }
                 })*/
        }
        this.loadData();
    },
    loadData: function () {
        let that = this;
        return WX
        // get user Info
            .userInfo(false)
            .then(res => {
                const userInfo = res.userInfo;
                app.globalData.userInfo = userInfo;

                that.setData({
                    hasUserInfo: true
                });

                if (!wx.getStorageSync('sessionOpenId')) {
                    /*WX.login().then(res => {
                        console.log("get jsCode to login" + res.code);
                        const userInfo = app.globalData.userInfo;
                        RecordService.getInstance().serverUserLogin(
                            res.code,
                            hashToInt(`${userInfo.nickName}-${userInfo.city}-${userInfo.province}-${userInfo.country}`),
                            wx.getStorageSync('sessionOpenId')
                        ).then(res => {
                            console.log("store the openid in app " + res.data.data)
                            console.dir(res);
                            app.globalData.sessionOpenId = res.data.data;
                            wx.setStorageSync('sessionOpenId', res.data.data);
                        })
                    })*/
                    WX.login()
                        .then(res => {
                            console.log("get jsCode to login" + res.code);
                            const userInfo = app.globalData.userInfo;
                            return RecordService.getInstance().serverUserLogin(
                                res.code,
                                hashToInt(`${userInfo.nickName}-${userInfo.city}-${userInfo.province}-${userInfo.country}`),
                                wx.getStorageSync('sessionOpenId'))
                        })
                        .then(res => {
                            console.log("store the openid in app " + res.data.data)
                            console.dir(res);
                            app.globalData.sessionOpenId = res.data.data;
                            wx.setStorageSync('sessionOpenId', res.data.data);
                            return UserService
                                .getInstance()
                                .createAndUpdateUser({
                                    userName: userInfo.nickName,
                                    groupId: 1,
                                    unionId: hashToInt(`${userInfo.nickName}-${userInfo.city}-${userInfo.province}-${userInfo.country}`),
                                    icon: userInfo.avatarUrl
                                });
                        }).then(res => {
                        const homePageModel = res.data.data;
                        that.setData({
                            userInfo: homePageModel.userRecord || {},
                            users: homePageModel.otherUsersRecord || []
                        });
                        Object.assign(app.globalData.userInfo, homePageModel.userRecord);
                    })
                }


                // create or update user
                return UserService
                    .getInstance()
                    .createAndUpdateUser({
                        userName: userInfo.nickName,
                        groupId: 1,
                        unionId: hashToInt(`${userInfo.nickName}-${userInfo.city}-${userInfo.province}-${userInfo.country}`),
                        icon: userInfo.avatarUrl
                    });
            }).then(res => {
                const homePageModel = res.data.data;
                that.setData({
                    userInfo: homePageModel.userRecord || {},
                    users: homePageModel.otherUsersRecord || []
                });
                Object.assign(app.globalData.userInfo, homePageModel.userRecord);
                return new Promise((resolve) => resolve());
            });
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
        const userInfo = this.data.userInfo;
        RecordService
            .getInstance()
            .submitRecord(userInfo.userId, record, 1, '')
            .then(res => {

                console.log("这是打卡的结果");
                console.dir(res);
                if (res.data.code === 0)
                    wx.showToast({title: 'Success!'})

                else if (res.data.code == 400) {
                    wx.showToast({
                            title: res.data.msg,
                            icon: 'none'
                        }
                    )
                }
                else {
                    wx.showToast({
                            title: 'UnKnown Exception',
                            icon: 'none'
                        }
                    )
                }

                var that = this;

                setTimeout(function () {
                    that
                        .loadData()
                        .then(() => wx.stopPullDownRefresh());
                    that.backdropMgt();
                }, 1500)

            });
    },


    /////////////////////////////////////////// double tap event ////////////////////////////////////

    touchStartTime: 0,
    touchEndTime: 0,
    lastTapTime: 0,
    lastTapTimeoutFunc: null,

    doubleTap: function (e) {

        const doubleTapAction = e.currentTarget.dataset.action || '';
        if (doubleTapAction === '') return;

        let that = this;
        that.lastTapTimeoutFunc = that[doubleTapAction];
        if (that.touchEndTime - that.touchStartTime < 350) {
            let currentTime = e.timeStamp
            let lastTapTime = that.lastTapTime
            that.lastTapTime = currentTime
            if (currentTime - lastTapTime < 300) {
                // console.log("double tap")
                that.lastTapTimeoutFunc(that.data.whoIsGonnaBeVote);
            }
        }
    },

    // voteUserId,
    // groupId,
    // voteUserGroupId,
    // status,
    // runningRecordId
    like: function (user) {
        const me = app.globalData.userInfo;
        const liked = this.data.liked;
        const voteStatus = liked ? 1 : 0;
        let that = this;
        UserService
            .getInstance()
            .voteUser(me.userId, 1, me.userGroupId, voteStatus, user.runningRecordId)
            .then(res => {
                wx.showToast({title: 'submit !'});
                const voteStatus = res.data.data.status;
                that.voteStatusChooser(voteStatus);
            });
    },

    dislike: function (user) {
        const me = app.globalData.userInfo;
        const disliked = this.data.disliked;
        const voteStatus = disliked ? 3 : 2;
        let that = this;
        UserService
            .getInstance()
            .voteUser(me.userId, 1, me.userGroupId, voteStatus, user.runningRecordId)
            .then(res => {
                wx.showToast({title: 'submit !'});
                const voteStatus = res.data.data.status;
                that.voteStatusChooser(voteStatus);
            });
    },

    /////////////////////////////////////////// double tap event ////////////////////////////////////
    onPullDownRefresh: function () {
        this
            .loadData()
            .then(() => wx.stopPullDownRefresh());
    },
});
