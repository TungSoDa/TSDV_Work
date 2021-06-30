package com.ifi.fresher_test.ifi_fresher_test.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResource {
    /**
     * For Const
     */
    public static final Integer ONE_TOPIC_EXAM_QUESTION_NUMBER = 3;
    public static final Integer ALL_TOPIC_EXAM_QUESTION_NUMBER = 18;

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
    public static final String THIS_QUESTION_CONTENT_WITH_TOPIC = "THIS QUESTION CONTENT WITH TOPIC";
    public static final String MAY_BE_THE_SAME_CONTENT_AS_EXISTING_QUESTION = "MAY BE THE SAME CONTENT AS EXISTING QUESTION";

    /**
     * For Exam
     */
    public static final String HTML = "HTML";
    public static final String JAVA_SCRIPT = "JavaScript";
    public static final String ANGULAR = "Angular";
    public static final String JAVA_BASIC = "Java Basic";
    public static final String JAVA_OOP = "Java OOP";
    public static final String JAVA_SPRING = "Java Spring";
    public static final String SYNTHESIS_TOPIC = "Synthesis";

    public static final String INCORRECT_QUESTION_LIST_WITH_TOPIC = "INCORRECT QUESTION LIST WITH TOPIC";

    /**
     * For Notice
     */
    public static final String ALREADY_EXISTS = "ALREADY EXISTS";
    public static final String NOT_CREATED_YET = "NOT CREATED YET";
    public static final String OR_IS_DELETED = "OR IS DELETED";
}
