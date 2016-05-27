package com.repitch.worldclasstest.network.models;

import java.util.List;

/**
 * Created by repitch on 25.05.16.
 */
public class ScheduleItem {
   /* "id": "СЕ-00003137",
            "groupId": "00000003542",
            "time": "2016-05-15 14:10",
            "name": "ВЫПРЯМЛЯЙ-КА 5-8",
            "description": "Занятие  для детей 5-8 лет. Упражнения и игровые задания, направленные на профилактику нарушений осанки. \r\n",
            "place": "Детский зал",
            "trainer": "Третьякова Марина Михайловна",
            "trainerId": "017908",
            "studentLevel": "Любой",
            "duration": 45,
            "isPreBooked": "false",
            "isPrePaid": "false",
            "ageGroups": [
    {
        "ageGroup": "Kids"
    }
    ]*/
    public String id;
    public String groupId;
    public String time; // date time
    public String name;
    public String description;
    public String place;
    public String trainer;
    public String trainerId;
    public String studentLevel;
    public int duration;
    public boolean isPreBooked;
    public boolean isPrePaid;
    public List<AgeGroup> ageGroups;
}
