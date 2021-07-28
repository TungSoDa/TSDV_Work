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
  /**
    * For Entity
    */
  ACCOUNT: "ACCOUNT",
  CONTESTANT: "CONTESTANT",
  CONTRIBUTOR: "CONTRIBUTOR",
  EXAM: "EXAM",
  QUESTION: "QUESTION",
  ANSWER: "ANSWER",

  /**
    * For Answer
    */
   QUESTION_HAS_NO_CORRECT_ANSWER_YET: "QUESTION HAS NO CORRECT ANSWER YET",
   ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION: "ANSWER CONTENT ALREADY EXISTS IN THIS QUESTION",
   ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION: "ONLY ONE CORRECT ANSWER IN THIS QUESTION",

   /**
    * For Question
    */
   THIS_QUESTION_CONTENT: "THIS QUESTION CONTENT",
   MAY_BE_THE_SAME_CONTENT_AS_EXISTING_QUESTION: "MAY BE THE SAME CONTENT AS EXISTING QUESTION",
   NO_QUESTIONS_WITH_THIS_TOPIC: "NO QUESTIONS WITH THIS TOPIC",

   /**
    * For Exam
    */
   HTML: "HTML",
   JAVA_SCRIPT: "JavaScript",
   ANGULAR: "Angular",
   JAVA_BASIC: "Java Basic",
   JAVA_OOP: "Java OOP",
   JAVA_SPRING: "Java Spring",
   SYNTHESIS_TOPIC: "Synthesis",

   INCORRECT_QUESTION_LIST_WITH_TOPIC: "INCORRECT QUESTION LIST WITH TOPIC",
   USER_ALREADY_TESTED_THIS_EXAM: "USER ALREADY TESTED THIS EXAM",

   /**
    * For Notice
    */
   ALREADY_EXISTS: "ALREADY EXISTS",
   NOT_CREATED_YET: "NOT CREATED YET",
   IS_EMPTY: "IS EMPTY",
   OR_IS_DELETED: "OR IS DELETED",
   OR_IS_NOT_DELETED_YET: "OR IS NOT DELETED YET",
   UNTESTED: "UNTESTED",

   /**
    * For Account
    */
   LOGIN_SUCCESS: "LOGIN SUCCESS",
   WRONG_USERNAME: "WRONG USERNAME",
   WRONG_PASSWORD: "WRONG PASSWORD",
}
