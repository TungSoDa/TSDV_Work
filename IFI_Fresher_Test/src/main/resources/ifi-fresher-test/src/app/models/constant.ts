export const HOSTNAME = {
  backend: "http://localhost:8080",
  frontend: "http://localhost:4242",
}

export const ROLE = {
  CONTESTANT: "CONTESTANT",
  CONTRIBUTOR: "CONTRIBUTOR",
}

export const PATH = {
  questionImage: "../../../assets/images/question/"
}

export const EXAM_QUESTION_NUMBER = {
  ONE_TOPIC: 3,
  ALL_TOPIC: 18
}

export const TOPIC = [
  { content: 'HTML', selected: true },
  { content: 'CSS', selected: true },
  { content: 'JavaScript', selected: true },
  { content: 'Bootstrap', selected: true },
  { content: 'Angular', selected: true },
  { content: 'Java Basic', selected: true },
  { content: 'Java Advanced', selected: true },
  { content: 'Java OOP', selected: true },
  { content: 'Java Spring', selected: true },
  { content: 'Database', selected: true },
  { content: 'IELTS', selected: true },
  { content: 'TOEIC', selected: true },
  { content: 'TOEFL', selected: true },
  { content: 'SAT', selected: true },
  { content: 'CEFR', selected: true }
]

export const MESSAGE_RESOURCE = {

  ONE_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE: "Số câu hỏi của đề thi 1 chủ đề phải là",
  ALL_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE: "Số câu hỏi của đề thi tổng hợp phải là",
  /**
    * For Entity
    */
  ACCOUNT : "Tài khoản",
  CONTESTANT : "Người dùng",
  CONTRIBUTOR : "Quản trị viên",
  EXAM : "Đề thi",
  QUESTION : "Câu hỏi",
  ANSWER : "Câu trả lời",

  /**
   * For Answer
   */
  QUESTION_HAS_NO_CORRECT_ANSWER_YET : "Câu hỏi này chưa có câu đáp án",
  ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION : "Nội dung của câu trả lời này đã có ở đáp án khác trong câu hỏi",
  OR_NOT_CHANGE_CONTENT_QUESTION: "hoặc nội dung câu hỏi chưa được thay đổi khi cập nhật",
  ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION : "Chỉ có thể có 1 đán án cho câu hỏi này",

  /**
   * For Question
   */
  THIS_QUESTION_CONTENT : "Nội dung câu hỏi này",
  MAY_BE_THE_SAME_CONTENT_AS_EXISTING_QUESTION : "có thể có nội dung tương tự với những câu hỏi đã có trong Ngân hàng câu hỏi",
  NO_QUESTIONS_WITH_THIS_TOPIC : "Không có câu hỏi nào với chủ đề ",
  QUESTION_EXISTED_IN_EXAM : "Câu hỏi này đã có trong đề thi, hãy chọn câu hỏi khác",

  /**
   * For Exam
   */
  HTML : "HTML",
  JAVA_SCRIPT : "JavaScript",
  ANGULAR : "Angular",
  JAVA_BASIC : "Java Basic",
  JAVA_OOP : "Java OOP",
  JAVA_SPRING : "Java Spring",
  SYNTHESIS_TOPIC : "Synthesis",

  INCORRECT_QUESTION_LIST_WITH_TOPIC : "Danh sách câu hỏi được chọn không đúng với chủ đề của Đề thi",
  USER_ALREADY_TESTED_THIS_EXAM : "Người dùng đã làm đề thi này rồi",
  WITH_QUESTION_LIST_IS_CREATED: "với danh sách câu hỏi trên đã được tạo",
  WITH_THE_SAME_NAME: "với tên tương tự",

  /**
    * For Notice
    */
  ALREADY_EXISTS : "đã tồn tại",
  NOT_CREATED_YET : "chưa được tạo",
  IS_EMPTY : "trống",
  OR_IS_DELETED : "hoặc đã bị xóa",
  OR_IS_NOT_DELETED_YET : "hoặc chưa bị xóa",
  UNTESTED : "chưa được kiểm tra",

  /**
    * For Account
    */
  LOGIN_SUCCESS : "Đăng nhập thành công",
  WRONG_USERNAME : "Sai tên đăng nhập",
  WRONG_PASSWORD : "Sai mật khẩu",
  
}
