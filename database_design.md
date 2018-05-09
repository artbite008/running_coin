# Running Coin Database Desgin

## Table#0 Group

| GroupId               | GroupName    | MetaData      |
| --------------------- | ------------ | ------------- |
| int(16)               | varchar(256) | varchar(2000) |
| Primary Key(Sequence) | Unique       |               |

## Table#1 User_Info

| UserId                | UserName     | Status          | Role         | Coins      | Icon | TotalDistance | MetaData      |
| --------------------- | ------------ | --------------- | ------------ | ---------- | ---- | ------------- | ------------- |
| int(16)               | varchar(256) | varchar(32)     | varchar(32)  | int(16)    | blob | float(9,1)    | varchar(2000) |
| Primary Key(Sequence) |              | Active/Inactive | Admin/Member | Default  0 |      |               |               |

## Table#2 User_Group

| UserGroupId           | GroupId     | UserId      |
| --------------------- | ----------- | ----------- |
| int(16)               | int(16)     | int(16)     |
| Primary Key(Sequence) | Foreign Key | Foreign Key |



## Table#3 Running_Record

| RuningRecordId        | UserGroupId | CreationTime | LastVotedTime | Status                            | Score  | SettledTime | EarnedCoins | Comments     | Evidence | Distance   |
| --------------------- | ----------- | ------------ | ------------- | --------------------------------- | ------ | ----------- | ----------- | ------------ | -------- | ---------- |
| int(16)               | int(16)     | TIMESTAMP    | TIMESTAMP     | varchar(32)                       | int(4) | TIMESTAMP   | int(16)     | varchar(256) | blob     | float(3,1) |
| Primary Key(Sequence) | Foreign Key |              |               | Submitted/Expired/Rejected/Passed |        |             |             |              |          |            |

## Table#4 Vote_Record

| VoteRecordId          | VoteUserGroupId | RuningRecordId | CanceledTime | Status         | Score  | Comments    | VotedTime |
| --------------------- | --------------- | -------------- | ------------ | -------------- | ------ | ----------- | --------- |
| int(16)               | int(16)         | int(16)        | TIMESTAMP    | varchar(32)    | int(4) | varchar(32) | TIMESTAMP |
| Primary Key(Sequence) | Foreign Key     | Foreign Key    |              | Voted/Canceled | 1 / -1 |             |           |

## Table#5 Target_Distance

| TargetDistanceId      | UserGroupId | CreationTime | TargetDistance |
| --------------------- | ----------- | ------------ | -------------- |
| int(16)               | int(16)     | TIMESTAMP    | float(3,1)     |
| Primary Key(Sequence) | Foreign Key |              |                |

# Database Connection Info
````
#database
spring.datasource.url=jdbc:mysql://47.96.173.94:3306/runningcoin?useUnicode=yes&useSSL=false
spring.datasource.username=root
spring.datasource.password=qwert6
````
