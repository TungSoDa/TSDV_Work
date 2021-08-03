import { QuestionImpl } from "./question-model";

export interface Exam {
  examID: number,
  name: string,
  topic: string,
  listQuestionID: string,
  questionList: QuestionImpl[]
}

export class ExamImpl implements Exam {
  examID!: number;
  name!: string;
  topic!: string;
  listQuestionID!: string;
  questionList!: QuestionImpl[];

  constructor() {}
}
