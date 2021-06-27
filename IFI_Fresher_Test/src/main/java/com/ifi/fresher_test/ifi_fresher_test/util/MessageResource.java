package com.ifi.fresher_test.ifi_fresher_test.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResource {
    /**
     * For Entity
     */
    public static final String ACCOUNT = "ACCOUNT";
    public static final String CONTESTANT = "CONTESTANT";
    public static final String CONTRIBUTOR = "CONTRIBUTOR";
    public static final String EXAM = "EXAM";
    public static final String QUESTION = "QUESTION";
    public static final String ANSWER = "ANSWER";

    /**
     * For Answer
     */
    public static final String QUESTION_HAS_NO_CORRECT_ANSWER_YET = "QUESTION HAS NO CORRECT ANSWER YET";
    public static final String ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION = "ANSWER CONTENT ALREADY EXISTS IN THIS QUESTION";
    public static final String ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION = "ONLY ONE CORRECT ANSWER IN THIS QUESTION";

    /**
     * For Question
     */
    public static final String QUESTION_CONTENT_WITH_TOPIC = "QUESTION CONTENT WITH TOPIC";

    /**
     * For Notice
     */
    public static final String ALREADY_EXISTS = "ALREADY EXISTS";
    public static final String NOT_CREATED_YET = "NOT CREATED YET";
}
