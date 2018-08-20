<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RunningClub Report</title>
</head>

<style type="text/css">
    table {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
    }

    td, th {
        font-size: 1em;
        border: 1px solid #5B4A42;
        padding: 3px 7px 2px 7px;
    }

    th {
        font-size: 1.1em;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #24A9E1;
        color: #ffffff;
    }
</style>
<body>
<div>
    <h2>Daily RunningClub Report</h2>
    <table id="customers">
        <tr>
            <th>UserName</th>
            <th>Distance</th>
            <th>CreationTime</th>
            <th>Status</th>
            <th>EarnedCoins</th>
        </tr>
        <#list MailBeanList as mailBean >
        <tr>
            <td>${(mailBean.username)!""}</td>
            <td>${(mailBean.distance)!""}</td>
            <td>${(mailBean.creationTime)!""}</td>
            <td>${(mailBean.status)!""}</td>
            <td>${(mailBean.earnedCoins)!""}</td>
        </tr>
        </#list>
    </table>

    <br/>
    <br/>

    <h2>Today Voted Report</h2>
    <table>
        <tr>
            <th>Username</th>
            <th>votedDate</th>
            <th>votedCount</th>
        </tr>
        <#list dailyVotedRecordList as dailyVotedRecord >
        <tr>
            <td>${(dailyVotedRecord.userName)!""}</td>
            <td>${(dailyVotedRecord.votedDate)?string('yyyy-MM-dd')}</td>
            <td>${(dailyVotedRecord.votedCount)!""}</td>
        </tr>
        </#list>
    </table>

    <h2>Weekly Awarded Report</h2>
    <table>
        <tr>
            <th>Username</th>
            <th>AwardDate</th>
            <th>EarnCoin</th>
        </tr>
        <#list weeklyAwardedList as weeklyAwarded >
        <tr>
            <td>${(weeklyAwarded.userName)!""}</td>
            <td>${(weeklyAwarded.awardDate)?string('yyyy-MM-dd')}</td>
            <td>${(weeklyAwarded.earnCoin)!""}</td>
        </tr>
        </#list>
    </table>


    <br/>
    <br/>

    <h2>User Info</h2>
    <table>
        <tr>
            <th>Username</th>
            <th>Role</th>
            <th>TotalDistance</th>
            <th>TotalCoins</th>
        </tr>
        <#list UserInfoList as userInfo >
        <tr>
            <td>${(userInfo.userName)!""}</td>
            <td>${(userInfo.role)!""}</td>
            <td>${(userInfo.totalDistance)!""}</td>
            <td>${(userInfo.coins)!""}</td>
        </tr>
        </#list>
    </table>
</div>
</body>
</html>