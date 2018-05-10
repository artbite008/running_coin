const users = [
  {
    id: 1,
    nickName: 'Danny',
    target: "10",
    current: "5",
    thumbUp: 8,
    thumbDown: 8,
    color: 'warning',
    coins: 100,
    latestRecord: 5
  },
  {
    id: 2,
    nickName: 'Speed',
    target: "10",
    current: "3",
    thumbUp: 8,
    thumbDown: 8,
    color: 'error',
    coins: 100,
    latestRecord: 1
  },
  {
    id: 3,
    nickName: 'Speed',
    target: "10",
    current: "8",
    thumbUp: 8,
    thumbDown: 8,
    color: 'info',
    coins: 100,
    latestRecord: 1
  },
  {
    id: 4,
    nickName: 'Speed',
    target: "13",
    current: "5",
    thumbUp: 8,
    thumbDown: 8,
    color: 'warning',
    coins: 100,
    latestRecord: 3
  },
  {
    id: 5,
    nickName: 'Speed',
    target: "10",
    current: "3",
    thumbUp: 8,
    thumbDown: 8,
    color: 'warning',
    coins: 100,
    latestRecord: 1
  },
  {
    id: 6,
    nickName: 'Speed',
    target: "10",
    current: "10",
    thumbUp: 8,
    thumbDown: 8,
    color: 'success',
    coins: 100,
    latestRecord: 5
  },
  {
    id: 7,
    nickName: 'Speed',
    target: "10",
    current: "9",
    thumbUp: 8,
    thumbDown: 8,
    color: 'success',
    coins: 100,
    latestRecord: 5
  },
  {
    id: 8,
    nickName: 'Speed',
    target: "10",
    current: "7",
    thumbUp: 8,
    thumbDown: 8,
    color: 'primary',
    coins: 100,
    latestRecord: 5
  }
];

const myWeeklyRecords =
  {
    range: '2018-4-9 MON to 2018-4-15 SUN',
    record: [{
      id: '00001',
      date: '2018-4-15 14:35 MON',
      distance: '5',
      vote: 10,
      devote: 5
    },
    {
      id: '00002',
      date: '2018-4-15 14:35 TUE',
      distance: '5',
      vote: 10,
      devote: 5
    },
    {
      id: '00003',
      date: '2018-4-15 14:35 WED',
      distance: '5',
      vote: 10,
      devote: 5
    },
    {
      id: '00004',
      date: '2018-4-15 14:35 THU',
      distance: '5',
      vote: 10,
      devote: 5
    },
    {
      id: '00005',
      date: '2018-4-15 14:35 FRI',
      distance: '5',
      vote: 10,
      devote: 5
    },
    {
      id: '00006',
      date: '2018-4-15 14:35 SAT',
      distance: '5',
      vote: 10,
      devote: 5
    },
    {
      id: '00007',
      date: '2018-4-15 14:35 SUN',
      distance: '5',
      vote: 10,
      devote: 5
    }]
  };

const allWeeklyRecords = [
  {
    id: '111111111',
    range: '2018-4-9 MON to 2018-4-15 SUN',
    record: [{
      id: '00001',
      achievements: [1, 2, 3, 4, 5, 7, 1],
      allAchievements: 20,
      allVote: 100,
      allDevote: 20
    },
    {
      id: '00002',
      achievements: [1, 2, 3, 4, 5, 8, 1],
      allAchievements: 20,
      allVote: 100,
      allDevote: 20
    },
    {
      id: '00003',
      achievements: [1, 2, 3, 4, 5, 9, 1],
      allAchievements: 20,
      allVote: 100,
      allDevote: 20
    },
    {
      id: '00004',
      achievements: [1, 2, 3, 4, 5, 9, 1],
      allAchievements: 20,
      allVote: 100,
      allDevote: 20
    },
    {
      id: '00005',
      achievements: [1, 2, 3, 4, 5, 8, 1],
      allAchievements: 20,
      allVote: 100,
      allDevote: 20
    },
    {
      id: '00006',
      achievements: [1, 2, 3, 4, 5, 8, 1],
      allAchievements: 20,
      allVote: 100,
      allDevote: 20
    },
    {
      id: '00007',
      achievements: [1, 2, 3, 4, 5, 8, 1],
      allAchievements: 20,
      allVote: 100,
      allDevote: 20
    }]
  }
];

const achievements = [
  3,
  4,
  5,
  6,
  7,
  8
];

const plans = [
  7,
  8,
  9,
  10,
  11,
  12,
  13,
  14,
  15,
  16,
  17,
  18,
  19,
  20,
  21,
  22,
  23,
  24,
  25
];

export { users, myWeeklyRecords, allWeeklyRecords, achievements, plans};