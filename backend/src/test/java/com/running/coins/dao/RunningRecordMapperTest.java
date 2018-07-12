package com.running.coins.dao;

import com.running.coins.common.util.DateUtils;
import com.running.coins.common.util.ThisLocalizedWeek;
import com.running.coins.model.transition.UserRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunningRecordMapperTest {

    @Autowired
    private RunningRecordMapper runningRecordMapper;

    @Test
    public void selectDailyUserRecord() {
        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        List<UserRecord> userRecords = runningRecordMapper.selectDailyUserRecord(thisLocalizedWeek.getFirstDay(), thisLocalizedWeek.getLastDay());

        for (UserRecord userRecord : userRecords) {
            System.out.println("=============="+userRecord);
        }
    }
}