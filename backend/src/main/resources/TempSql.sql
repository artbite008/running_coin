SELECT
  Running_Record.RuningRecordId    AS runningRecordId,
  User_Info.UserId                 AS userId,
  UserGroup.UserGroupId            AS userGroupId,
  User_Info.UserName               AS nickName,
  User_Info.Coins                  AS coins,
  User_Info.Icon                   AS icon,
  Running_Record.Distance          AS current,
  WeekilyTarget.TargetDistance     AS target,
  VoteResult.likes                 AS likes,
  VoteResult.dislikes              AS dislikes,
  RecordHasVoted.distancePassVoted AS distancePassVoted,
  RecordWaitVoted.distanceWaitVote AS distanceWaitVote,
  Running_Record.Status            AS status

FROM Running_Record
  LEFT JOIN UserGroup ON UserGroup.UserGroupId = Running_Record.UserGroupId
  LEFT JOIN User_Info ON UserGroup.UserId = User_Info.UserId
  LEFT JOIN (
    SELECT *
    FROM Target_Distance
    WHERE CreationTime > '2018-05-14 00:00:00' AND CreationTime < '2018-05-20 23:59:59'
    ) as WeekilyTarget ON UserGroup.UserGroupId = WeekilyTarget.UserGroupId
  LEFT JOIN (
              SELECT
                RuningRecordId,
                sum(CASE WHEN Score = 1
                  THEN 1
                    ELSE 0 END) AS likes,
                sum(CASE WHEN Score = -1
                  THEN 1
                    ELSE 0 END) AS dislikes
              FROM Vote_Record
              WHERE VotedTime > '2018-05-14 00:00:00' AND VotedTime < '2018-05-20 23:59:59'
              GROUP BY RuningRecordId
            ) AS VoteResult ON VoteResult.RuningRecordId = Running_Record.RuningRecordId
  LEFT JOIN (
              SELECT
                UserGroupId,
                sum(Distance) AS distancePassVoted
              FROM Running_Record
              WHERE CreationTime > '2018-05-14 00:00:00' AND CreationTime < '2018-05-20 23:59:59'
                    AND Status = 3
              GROUP BY UserGroupId
            ) AS RecordHasVoted ON RecordHasVoted.UserGroupId = UserGroup.UserGroupId
  LEFT JOIN (
              SELECT
                UserGroupId,
                sum(Distance) AS distanceWaitVote
              FROM Running_Record
              WHERE CreationTime > '2018-05-14 00:00:00' AND CreationTime < '2018-05-20 23:59:59'
                    AND Status IS NULL
              GROUP BY UserGroupId
            ) AS RecordWaitVoted ON RecordWaitVoted.UserGroupId = UserGroup.UserGroupId
WHERE Running_Record.CreationTime > '2018-05-14 00:00:00' AND Running_Record.CreationTime < '2018-05-20 23:59:59'
      AND Running_Record.Status = 0