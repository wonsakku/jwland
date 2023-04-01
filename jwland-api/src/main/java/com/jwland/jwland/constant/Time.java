package com.jwland.jwland.constant;

public interface Time {

    interface RegExp{
        String YYYYMMDD = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";

    }


    interface Pattern{
       String DEFAULT_PATTERN = "YYYY-MM-DD HH:mm:ss";
    }

    interface Zone{
        String ASIA_SEOUL = "Asia/Seoul";
    }


}
