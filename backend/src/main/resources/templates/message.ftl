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
    <h2>RunningClub Report</h2>
    <table id="customers">
        <tr>
            <th>UserName</th>
            <th>Distance</th>
            <th>CreationTime</th>
            <th>Status</th>
        </tr>
        <#list MailBeanList as mailBean >
        <tr>
            <td>${(mailBean.username)!""}</td>
            <td>${(mailBean.distance)!""}</td>
            <td>${(mailBean.creationTime)!""}</td>
            <td>${(mailBean.status)!""}</td>
        </tr>
        </#list>
    </table>
</div>
</body>
</html>