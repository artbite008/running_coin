## 删除无用UserGroup 的信息
``` sql
delete FROM UserGroup where (select OpenId FROM User_Info WHERE UserId = UserGroup.UserOpenId) is NULL ;
```

## 将UserGroup的 Userid 更新为 UserOpenId
``` sql
update UserGroup LEFT JOIN User_Info on UserOpenId = User_Info.UserId set UserOpenId = User_Info.OpenId
```