package com.ifi.fresher_test.ifi_fresher_test.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResource {
    /**
     * For Const
     */
    public static final String questionImage = "../resources/ifi-fresher-test/src/assets/images/question/";
    public static final Integer ONE_TOPIC_EXAM_QUESTION_NUMBER = 3;
    public static final Integer ALL_TOPIC_EXAM_QUESTION_NUMBER = 18;
    public static final String ONE_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE = "Số câu hỏi của đề thi 1 chủ đề phải là";
    public static final String ALL_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE = "Số câu hỏi của đề thi tổng hợp phải là";

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
    public static final String SHOW_ALL_ANSWER = "Đã hiện danh sách tất cả câu trả lời";
    public static final String SHOW_ANSWER_BY_ID = "Đã hiện đáp án với mã câu trả lời là";
    public static final String SHOW_LIST_ANSWER_OF_QUESTION = "Đã hiện tất cả câu trả lời của câu hỏi";
    public static final String SHOW_CORRECTED_ANSWER_OF_QUESTION = "Đã hiện đáp án của câu hỏi";
    public static final String ADD_ANSWER_SUCCESSFUL = "Thêm mới câu trả lời thành công";
    public static final String EDIT_ANSWER_SUCCESSFUL = "Sửa câu trả lời thành công";
    public static final String DELETE_ANSWER_SUCCESSFUL = "Xóa câu trả lời thành công";
    public static final String QUESTION_HAS_NO_CORRECT_ANSWER_YET = "Câu hỏi này chưa có câu đáp án";
    public static final String ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION = "Nội dung của câu trả lời này đã có ở đáp án khác trong câu hỏi";
    public static final String OR_NOT_CHANGE_CONTENT_QUESTION = "hoặc nội dung câu hỏi chưa được thay đổi khi cập nhật";
    public static final String ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION = "Chỉ có thể có 1 đán án cho câu hỏi này";

    /**
     * For Question
     */
    public static final String SHOW_ALL_QUESTION = "Đã hiện danh sách tất cả câu hỏi";
    public static final String SHOW_QUESTION_BY_ID = "Đã hiện câu hỏi với mã câu hỏi là";
    public static final String SHOW_LIST_QUESTION_BY_TOPIC = "Đã hiện danh sách câu hỏi với chủ đề là";
    public static final String ADD_QUESTION_SUCCESSFUL = "Thêm mới câu hỏi thành công";
    public static final String EDIT_QUESTION_SUCCESSFUL = "Sửa câu hỏi thành công";
    public static final String DELETE_QUESTION_SUCCESSFUL = "Xóa câu hỏi thành công";
    public static final String THIS_QUESTION_CONTENT = "Nội dung câu hỏi này";
    public static final String NO_QUESTIONS_WITH_THIS_TOPIC = "Không có câu hỏi nào với chủ đề ";

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

    public static final String SHOW_ALL_EXAM = "Đã hiện tất cả đề thi";
    public static final String SHOW_EXAM_BY_ID = "Đã hiện đề thi với mã đề thi là";
    public static final String SHOW_LIST_EXAM_BY_TOPIC = "Đã hiện danh sách đề thi với chủ đề là";
    public static final String ADD_EXAM_SUCCESSFUL = "Thêm mới đề thi thành công";
    public static final String EDIT_EXAM_SUCCESSFUL = "Sửa đề thi thành công";
    public static final String DELETE_EXAM_SUCCESSFUL = "Xóa đề thi thành công";
    public static final String INCORRECT_QUESTION_LIST_WITH_TOPIC = "Danh sách câu hỏi được chọn không đúng với chủ đề của Đề thi";
    public static final String USER_ALREADY_TESTED_THIS_EXAM = "Người dùng đã làm đề thi này rồi";
    public static final String WITH_QUESTION_LIST_IS_CREATED = "với danh sách câu hỏi trên đã được tạo";
    public static final String WITH_THE_SAME_NAME = "với tên tương tự";

    /**
     * For Exam Result
     */
    public static final String SHOW_ALL_EXAM_RESULT = "Đã hiện tất cả kết quả kiểm tra";
    public static final String SHOW_EXAM_RESULT_BY_ID = "Đã hiện kết quả kiểm tra với mã kết quả kiểm tra là";
    public static final String SHOW_EXAM_RESULT_BY_EXAM_ID_AND_USERNAME = "Đã hiện kết quả kiểm tra với mã đề thi và tên người thi là";
    public static final String ADD_EXAM_RESULT_SUCCESSFUL = "Thêm mới kết quả kiểm tra thành công";
    public static final String DELETE_EXAM_RESULT_SUCCESSFUL = "Xóa kết quả kiểm tra thành công";

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
    public static final String SHOW_ALL_ACCOUNT = "Đã hiện thông tin tất cả tài khoản";
    public static final String SHOW_ACCOUNT_BY_USERNAME = "Đã hiện thông tin tài khoản với tên người dùng là";
    public static final String ADD_ACCOUNT_SUCCESSFUL = "Thêm mới tài khoản thành công";
    public static final String EDIT_ACCOUNT_SUCCESSFUL = "Sửa tài khoản thành công";
    public static final String DELETE_ACCOUNT_SUCCESSFUL = "Xóa tài khoản thành công";
    public static final String LOGIN_SUCCESS = "Đăng nhập thành công";
    public static final String WRONG_USERNAME = "Sai tên đăng nhập";
    public static final String WRONG_PASSWORD = "Sai mật khẩu";

    /**
     * For Contestant
     */
    public static final String SHOW_ALL_CONTESTANT = "Đã hiện thông tin tất cả người làm bài";
    public static final String SHOW_CONTESTANT_BY_USERNAME = "Đã hiện thông tin người làm bài với tên người dùng là";
    public static final String ADD_CONTESTANT_SUCCESSFUL = "Thêm mới thông tin người làm bài thành công";
    public static final String EDIT_CONTESTANT_SUCCESSFUL = "Sửa thông tin người làm bài thành công";
    public static final String DELETE_CONTESTANT_SUCCESSFUL = "Xóa thông tin người làm bài thành công";

    /**
     * For Contributor
     */
    public static final String SHOW_ALL_CONTRIBUTOR = "Đã hiện thông tin tất cả cán bộ hướng dẫn";
    public static final String SHOW_CONTRIBUTOR_BY_USERNAME = "Đã hiện thông tin cán bộ hướng dẫn với tên người dùng là";
    public static final String ADD_CONTRIBUTOR_SUCCESSFUL = "Thêm mới thông tin cán bộ hướng dẫn thành công";
    public static final String EDIT_CONTRIBUTOR_SUCCESSFUL = "Sửa thông tin cán bộ hướng dẫn thành công";
    public static final String DELETE_CONTRIBUTOR_SUCCESSFUL = "Xóa thông tin cán bộ hướng dẫn thành công";
}
