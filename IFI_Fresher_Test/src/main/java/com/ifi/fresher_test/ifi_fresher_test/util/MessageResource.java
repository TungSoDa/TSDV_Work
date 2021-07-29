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
    public static final String ACCOUNT = "Tài khoản";
    public static final String CONTESTANT = "Người dùng";
    public static final String CONTRIBUTOR = "Quản trị viên";
    public static final String EXAM = "Đề thi";
    public static final String QUESTION = "Câu hỏi";
    public static final String ANSWER = "Câu trả lời";

    /**
     * For Answer
     */
    public static final String QUESTION_HAS_NO_CORRECT_ANSWER_YET = "Câu hỏi này chưa có câu đáp án";
    public static final String ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION = "Nội dung của câu trả lời này đã có ở đáp án khác trong câu hỏi";
    public static final String ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION = "Chỉ có thể có 1 đán án cho câu hỏi này";

    /**
     * For Question
     */
    public static final String THIS_QUESTION_CONTENT = "Nội dung câu hỏi này";
    public static final String MAY_BE_THE_SAME_CONTENT_AS_EXISTING_QUESTION = "có thể có nội dung tương tự với những câu hỏi đã có trong Ngân hàng câu hỏi";
    public static final String NO_QUESTIONS_WITH_THIS_TOPIC = "Không có câu hỏi nào với chủ đề này";

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

    public static final String INCORRECT_QUESTION_LIST_WITH_TOPIC = "Danh sách câu hỏi được chọn không đúng với chủ đề của Đề thi";
    public static final String USER_ALREADY_TESTED_THIS_EXAM = "Người dùng đã làm đề thi này rồi";

    /**
     * For Notice
     */
    public static final String ALREADY_EXISTS = "đã tồn tại";
    public static final String NOT_CREATED_YET = "chưa được tạo";
    public static final String IS_EMPTY = "trống";
    public static final String OR_IS_DELETED = "hoặc đã bị xóa";
    public static final String OR_IS_NOT_DELETED_YET = "hoặc chưa bị xóa";
    public static final String UNTESTED = "chưa được kiểm tra";

    /**
     * For Account
     */
    public static final String LOGIN_SUCCESS = "Đăng nhập thành công";
    public static final String WRONG_USERNAME = "Sai tên đăng nhập";
    public static final String WRONG_PASSWORD = "Sai mật khẩu";
}
