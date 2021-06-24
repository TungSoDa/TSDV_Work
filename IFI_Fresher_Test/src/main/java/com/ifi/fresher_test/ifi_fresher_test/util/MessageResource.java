package com.ifi.fresher_test.ifi_fresher_test.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResource {
    /**
     * For add api
     */
    public static final String ACCOUNT_ALREADY_EXISTS = "ACCOUNT_ALREADY_EXISTS";
    public static final String CONTESTANT_ALREADY_EXISTS = "CONTESTANT_ALREADY_EXISTS";
    public static final String CONTRIBUTOR_ALREADY_EXISTS = "CONTRIBUTOR_ALREADY_EXISTS";
    public static final String EXAM_ALREADY_EXISTS = "EXAM_ALREADY_EXISTS";
    public static final String QUESTION_ALREADY_EXISTS = "QUESTION_ALREADY_EXISTS";
    public static final String ANSWER_ALREADY_EXISTS = "ANSWER_ALREADY_EXISTS";

    public static final String ACCOUNT_NOT_CREATED_YET = "ACCOUNT_NOT_CREATED_YET";

    /**
     * For exception
     */
    public static final String IO_EXCEPTION = "IO_EXCEPTION";

    /**
     * For role
     */
    public static final String CONTESTANT = "CONTESTANT";
    public static final String CONTRIBUTOR = "CONTRIBUTOR";
}
