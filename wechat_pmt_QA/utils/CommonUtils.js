import {UserService, WxService as WX, RecordService} from "../pages/service/index.js";

export function getTheHomePageModel() {
    const sessionOpenId = wx.getStorageSync('sessionOpenId');
    if (sessionOpenId == null || sessionOpenId.length < 28) {
        return queryOpenId()
            .then(openId => queryUserInfoByOpenId(openId))
    } else {
        return queryUserInfoByOpenId(sessionOpenId)
    }
}

export function queryOpenId() {
    return new Promise((resolve, reject) => {
        let nickName = wx.getStorageSync("nickName");
        let avatarUrl = wx.getStorageSync("avatarUrl");

        WX.login()
            .then(res => {
                console.log("get jsCode to login" + res.code);
                return RecordService.getInstance().serverUserLoginV2(
                    res.code,
                    nickName,
                    avatarUrl
                )
            })
            .then(res => {
                console.log("the message " + res);
                wx.setStorageSync('sessionOpenId', res.data.data);
                resolve(res.data.data);
            })
            .catch(res => {console.log(res)})
    });
}


export function queryUserInfoByOpenId(openId) {
    return new Promise((resolve, reject) => {
        UserService
            .getInstance()
            .createAndUpdateUser({
                userName: null,
                groupId: 1,
                openId: openId,
                icon: null
            })
            .then(res => {
                const homePageModel = res.data.data;
                resolve(homePageModel)
            });
    });

}

