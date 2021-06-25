package com.ifi.fresher_test.ifi_fresher_test.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResource {
    /**
     * For API
     */
    public static final String ACCOUNT_ALREADY_EXISTS = "ACCOUNT_ALREADY_EXISTS";
    public static final String CONTESTANT_ALREADY_EXISTS = "CONTESTANT_ALREADY_EXISTS";
    public static final String CONTRIBUTOR_ALREADY_EXISTS = "CONTRIBUTOR_ALREADY_EXISTS";
    public static final String EXAM_ALREADY_EXISTS = "EXAM_ALREADY_EXISTS";
    public static final String QUESTION_ALREADY_EXISTS = "QUESTION_ALREADY_EXISTS";
    public static final String ANSWER_ALREADY_EXISTS = "ANSWER_ALREADY_EXISTS";

    public static final String ACCOUNT_NOT_CREATED_YET = "ACCOUNT_NOT_CREATED_YET";
    public static final String CONTESTANT_NOT_CREATED_YET = "CONTESTANT_NOT_CREATED_YET";
    public static final String CONTRIBUTOR_NOT_CREATED_YET = "CONTRIBUTOR_NOT_CREATED_YET";
    public static final String QUESTION_NOT_CREATED_YET = "QUESTION_NOT_CREATED_YET";
    public static final String ANSWER_NOT_CREATED_YET = "ANSWER_NOT_CREATED_YET";

    public static final String ANSWER_ID_ALREADY_EXISTS = "ANSWER_ID_ALREADY_EXISTS";
    public static final String ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION = "ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION";
    public static final String ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION = "ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION";
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
